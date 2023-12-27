package dataTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.StampedLock;

public class DataTree {
    private final DataNode root;
    private final ConcurrentHashMap<String, DataNode> pathMap;
    private final StampedLock lock;
    private final WatcherManager watcherManager;

    public DataTree() {
        //创建根节点，生成根节点的信息
        long currentTime = System.currentTimeMillis();
        StatPersisted rootStat = new StatPersisted(0, 0, currentTime, currentTime, 0, 0);
        this.root = new DataNode(null, "root", rootStat);
        this.pathMap = new ConcurrentHashMap<>();
        this.pathMap.put("/", root);
        this.lock = new StampedLock();
        this.watcherManager = new WatcherManager();
    }

    //为树结构添加节点
    public DataNode addNode(String parentPrincipal, String nodePath, Object nodeData, StatPersisted nodeStat) {
        long stamp = lock.writeLock();
        try {
            // 按照分隔符沿树干遍历
            String[] parts = nodePath.split("/");
            DataNode current = root;

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
                    // 使用 WatcherManager 通知监听者
                    watcherManager.notifyWatchers(nodePath, EventType.NODE_CREATED);
                }
                current = child;
            }
            return current;
        } finally {
            lock.unlockWrite(stamp);
        }
    }


    //修改节点方法
    public boolean modifyNode(String parentPrincipal, String nodePath, Object nodeData, StatPersisted nodeStat) {
        long stamp = lock.writeLock();
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
                watcherManager.notifyWatchers(nodePath, EventType.NODE_DATA_CHANGED);
            }
            return isDataModified;
        } finally {
            lock.unlockWrite(stamp);
        }
    }


    // 查找节点方法
    public DataNode findNode(String nodePath) {
        long stamp = lock.tryOptimisticRead();
        try {
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
        } finally {
            if (!lock.validate(stamp)) {
                stamp = lock.readLock();
                try {
                    return pathMap.get(nodePath); // 再次尝试从pathMap获取
                } finally {
                    lock.unlockRead(stamp);
                }
            }
        }
    }


    // 删除节点方法，欠缺监听器的实现
    public boolean removeNode(String parentPrincipal, String nodePath) {
        long stamp = lock.writeLock();
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
                watcherManager.notifyWatchers(nodePath, EventType.NODE_DELETED);
            }
            return removed;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public void addWatcher(String path, Watcher watcher) {
        watcherManager.addWatcher(path, watcher);
    }

    public void removeWatcher(String path, Watcher watcher) {
        watcherManager.removeWatcher(path, watcher);
    }

    public boolean containsWatch(String path, Watcher watcher) {
        return watcherManager.containsWatcher(path, watcher);
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
        String watcherInfo = getWatchersInfo(path, indent.toString());

        System.out.println(indent + (level > 0 ? "- " : "") + "Path: " + path +
                ", Data: " + node.getData() + ", Permissions: " + node.getAcl() +
                ", Stat: " + node.getStat() + watcherInfo);

        for (Map.Entry<String, DataNode> entry : node.getChildren().entrySet()) {
            String childPath = path.equals("/") ? path + entry.getKey() : path + "/" + entry.getKey();
            printTreeNode(entry.getValue(), level + 1, childPath);
        }
    }

    private String getWatchersInfo(String path, String indent) {
        List<Watcher> watchers = watcherManager.getWatchers(path);
        if (watchers != null && !watchers.isEmpty()) {
            StringBuilder watchersInfo = new StringBuilder();
            watchersInfo.append(indent).append(" Watchers: [");
            for (Watcher watcher : watchers) {
                watchersInfo.append(watcher.getType()).append(", ");
            }
            watchersInfo.setLength(watchersInfo.length() - 2); // 移除最后的逗号和空格
            watchersInfo.append("]");
            return watchersInfo.toString();
        }
        return "";
    }


}

