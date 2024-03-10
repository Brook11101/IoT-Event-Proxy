package znode.dataTree;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataTree {
    private final DataNode root;
    private final ConcurrentHashMap<String, DataNode> pathMap;
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final WatcherManager dataWatches;
    private final WatcherManager childWatches;

    public DataTree() {
        //创建根节点，生成根节点的信息
        long currentTime = System.currentTimeMillis();
        StatPersisted rootStat = new StatPersisted(0, 0, currentTime, currentTime, 0, 0);
        this.root = new DataNode(null, "root", rootStat);
        this.pathMap = new ConcurrentHashMap<>();
        this.pathMap.put("/", root);
        this.dataWatches = new WatcherManager();
        this.childWatches = new WatcherManager();
    }

    //为树结构添加节点
    public DataNode addNode(String parentPrincipal, String nodePath, Object nodeData, StatPersisted nodeStat) {
        rwLock.writeLock().lock();
        try {
            // 按照分隔符沿树干遍历
            String[] parts = nodePath.split("/");
            DataNode current = root;
            boolean isNewNodeCreated = false;
            for (int i = 1; i < parts.length; i++) {
                if (parts[i].isEmpty()) continue;
                DataNode child = current.getChildren().get(parts[i]);
                if (child == null) {
                    //每一个节点的认证实际上凭借其父亲
                    child = new DataNode(current, nodeData, nodeStat);
                    current.addChild(parentPrincipal, parts[i], child);
                    //在map中存储了nodePath
                    pathMap.put(nodePath, child); // 更新路径映射

                    // 为新创建的节点设置权限
                    child.getAcl().addPermission(parentPrincipal, Permission.WRITE);
                    child.getAcl().addPermission(parentPrincipal, Permission.READ);
                    child.getAcl().addPermission(parentPrincipal, Permission.DELETE);
                    // 设置数据
                    child.setData(parentPrincipal, nodeData, nodeStat);
                    isNewNodeCreated = true;
                }
                current = child;
            }
            if (isNewNodeCreated) {
                // 触发新节点上的数据变化Watcher
                dataWatches.triggerWatchers(nodePath, EventType.NODE_CREATED, current);

                // 触发父节点的子节点列表变化Watcher
                String parentPath = nodePath.substring(0, nodePath.lastIndexOf('/'));
                parentPath = parentPath.isEmpty() ? "/" : parentPath;
                childWatches.triggerWatchers(parentPath, EventType.NODE_CHILDREN_CHANGED, current);
            }
            return current;
        } finally {
            rwLock.writeLock().unlock();
        }
    }


    //修改节点方法
    public boolean modifyNode(String parentPrincipal, String nodePath, Object nodeData, StatPersisted nodeStat) {
        rwLock.writeLock().lock();
        try {
            DataNode node = pathMap.get(nodePath);
            if (node == null) {
                // 如果pathMap中找不到节点，执行逐级查找
                String[] parts = nodePath.split("/");
                node = root;
                for (int i = 1; i < parts.length; i++) {
                    if (parts[i].isEmpty()) continue;
                    node = node.getChildren().get(parts[i]);
                    if (node == null) {
                        return false; // 节点路径不存在
                    }
                }
            }

            // 找到节点后的处理逻辑...
            boolean isDataModified = node.setData(parentPrincipal, nodeData, nodeStat);
            if (isDataModified) {
                //修改节点只需要触发数据watches的变化就行
                // 触发节点数据变化的Watcher
                dataWatches.triggerWatchers(nodePath, EventType.NODE_DATA_CHANGED, node);
            }
            return isDataModified;
        } finally {
            rwLock.writeLock().unlock();
        }
    }


    // 查找节点方法
    public DataNode findNode(String nodePath) {
        // 首先检查当前线程是否已经持有写锁
        if (rwLock.isWriteLockedByCurrentThread()) {
            return getNodeWithoutLock(nodePath);
        }

        rwLock.readLock().lock();
        try {
            return getNodeWithoutLock(nodePath);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    private DataNode getNodeWithoutLock(String nodePath) {
        DataNode node = pathMap.get(nodePath);
        if (node != null) {
            return node; // 从pathMap直接找到节点
        }

        // 如果在pathMap中找不到，执行逐级查找
        String[] parts = nodePath.split("/");
        node = root;
        for (int i = 1; i < parts.length; i++) {
            if (parts[i].isEmpty()) continue;
            node = node.getChildren().get(parts[i]);
            if (node == null) {
                return null; // 节点路径不存在
            }
        }

        // 将找到的节点加入pathMap，以便下次直接查找
        pathMap.putIfAbsent(nodePath, node);
        return node;
    }


    // 删除节点方法，欠缺监听器的实现
    public boolean removeNode(String parentPrincipal, String nodePath) {
        rwLock.writeLock().lock();
        try {
            if (nodePath.equals("/")) {
                throw new IllegalArgumentException("Cannot remove the root node.");
            }

            String[] parts = nodePath.split("/");
            DataNode current = root;
            DataNode parent = null;
            String childName = "";

            // 逐级找到要删除的节点及其父节点
            for (int i = 1; i < parts.length; i++) {
                if (parts[i].isEmpty()) continue;

                parent = current;
                childName = parts[i];
                current = current.getChildren().get(childName);

                if (current == null) {
                    return false; // 节点路径不存在
                }
            }

            if (!current.getAcl().hasPermission(parentPrincipal, Permission.DELETE)) {
                throw new SecurityException("No delete permission for user: " + parentPrincipal);
            }

            // 执行删除操作
            boolean removed = parent.removeChild(parentPrincipal, childName);
            if (removed) {
                pathMap.remove(nodePath); // 更新路径映射

                // 触发节点删除的Watcher
                dataWatches.triggerWatchers(nodePath, EventType.NODE_DELETED, null);

                // 触发父节点子节点列表变化的Watcher
                String parentPath = nodePath.substring(0, nodePath.lastIndexOf('/'));
                parentPath = parentPath.isEmpty() ? "/" : parentPath;
                childWatches.triggerWatchers(parentPath, EventType.NODE_CHILDREN_CHANGED, null);
            }
            return removed;
        } finally {
            rwLock.writeLock().unlock();
        }
    }


    public void watchData(String path, Watcher watcher) {
        if (watcher != null && pathMap.containsKey(path)) {
            dataWatches.addWatcher(path, watcher);
        }
    }

    public void watchChildren(String path, Watcher watcher) {
        if (watcher != null && pathMap.containsKey(path)) {
            childWatches.addWatcher(path, watcher);
        }
    }

    public void watchDataRemove(String path, Watcher watcher) {
        if (watcher != null && pathMap.containsKey(path)) {
            dataWatches.removeWatcher(path, watcher);
        }
    }

    public void watchChildrenRemove(String path, Watcher watcher) {
        if (watcher != null && pathMap.containsKey(path)) {
            childWatches.removeWatcher(path, watcher);
        }
    }

    private void updatePathMap(String path, DataNode node, boolean remove) {
        if (remove) {
            pathMap.remove(path);
        } else {
            pathMap.put(path, node);
        }
    }


    public void printTree() {
        printTreeNode(root, 0, "/");
    }

    private void printTreeNode(DataNode node, int level, String path) {
        if (node == null) return;

        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("|  ");
        }

        // 获取与节点相关的Watcher信息
        String dataWatcherInfo = getWatchersInfo(path, indent.toString(), dataWatches);
        String childWatcherInfo = getWatchersInfo(path, indent.toString(), childWatches);

        System.out.println(indent + (level > 0 ? "- " : "") + "Path: " + path +
                ", Data: " + node.getData() + ", Permissions: " + node.getAcl() +
                ", Stat: " + node.getStat() + dataWatcherInfo + childWatcherInfo);

        for (Map.Entry<String, DataNode> entry : node.getChildren().entrySet()) {
            String childPath = path.equals("/") ? path + entry.getKey() : path + "/" + entry.getKey();
            printTreeNode(entry.getValue(), level + 1, childPath);
        }
    }

    private String getWatchersInfo(String path, String indent, WatcherManager watcherManager) {
        List<Watcher> watchers = watcherManager.getWatchers(path);
        if (watchers != null && !watchers.isEmpty()) {
            StringBuilder watchersInfo = new StringBuilder();
            watchersInfo.append(indent).append(" Watchers: [");
            for (Watcher watcher : watchers) {
                watchersInfo.append(watcher.getEventType()).append(", ");
            }
            watchersInfo.setLength(watchersInfo.length() - 2); // 移除最后的逗号和空格
            watchersInfo.append("]");
            return watchersInfo.toString();
        }
        return "";
    }


}

