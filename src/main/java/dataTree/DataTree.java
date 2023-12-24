package dataTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.StampedLock;

public class DataTree {
    private final DataNode root;
    private final CopyOnWriteArrayList<DataNodeListener> listeners;
    private final StampedLock lock;

    public DataTree() {
        //创建根节点，生成根节点的信息
        long currentTime = System.currentTimeMillis();
        StatPersisted rootStat = new StatPersisted(0, 0, currentTime, currentTime, 0, 0);
        this.root = new DataNode(null, "root", rootStat);
        this.listeners = new CopyOnWriteArrayList<>();
        this.lock = new StampedLock();
    }

    //为树结构添加节点
    public DataNode addNode(String parentPrincipal, String nodePath, Object nodeData, StatPersisted nodeStat) {
        //首先在遍历树干阶段，尽可能使用读锁，提高并发程度
        long stamp = lock.readLock();
        try {
            //按照分隔符沿树干遍历
            String[] parts = nodePath.split("/");
            DataNode current = root;

            for (int i = 1; i < parts.length; i++) {
                if (parts[i].isEmpty()) continue;
                DataNode child = current.getChildren().get(parts[i]);
                if (child == null) {
                    long writeStamp = lock.tryConvertToWriteLock(stamp);
                    if (writeStamp != 0) {
                        stamp = writeStamp;
                        child = new DataNode(current, nodeData, nodeStat);
                        current.addChild(parentPrincipal, parts[i], child);
                    } else {
                        lock.unlockRead(stamp);
                        stamp = lock.writeLock();
                        // 重复添加节点的逻辑，因为我们失去了读锁并重新获取了写锁
                    }
                }
                current = child;
            }
            //认为创建这个子节点的线程默认拥有其所有权限
            current.getAcl().addPermission(parentPrincipal, Permission.WRITE);
            current.getAcl().addPermission(parentPrincipal, Permission.READ);
            current.getAcl().addPermission(parentPrincipal, Permission.DELETE);
            //设置数据
            current.setData(parentPrincipal, nodeData, nodeStat);
            notifyListeners(current, EventType.NODE_CREATED);
            return current;
        } finally {
            lock.unlock(stamp);
        }
    }

    //修改节点方法
    public boolean modifyNode(String parentPrincipal, String nodePath, Object nodeData, StatPersisted nodeStat) {
        long stamp = lock.writeLock();  // 使用写锁来确保数据修改的原子性和一致性
        try {
            String[] parts = nodePath.split("/");
            DataNode current = root;

            for (int i = 1; i < parts.length; i++) {
                if (parts[i].isEmpty()) continue;
                current = current.getChildren().get(parts[i]);
                if (current == null) {
                    return false; // 节点不存在
                }
            }

            // 直接在找到的节点上设置新数据
            boolean isDataModified = current.setData(parentPrincipal, nodeData, nodeStat);
            // 如果数据修改成功，通知监听器
            if (isDataModified) {
                notifyListeners(current, EventType.NODE_MODIFIED);
            }
            return isDataModified;
        } finally {
            lock.unlockWrite(stamp);
        }
    }


    // 查找节点方法
    public DataNode findNode(String nodePath) {
        long stamp = lock.tryOptimisticRead();
        String[] parts = nodePath.split("/");
        DataNode current = root;

        // 乐观读
        for (int i = 1; i < parts.length; i++) {
            if (parts[i].isEmpty()) continue;
            current = current.getChildren().get(parts[i]);
            if (current == null) {
                return null;
            }
        }

        // 验证乐观读
        if (!lock.validate(stamp)) {
            stamp = lock.readLock(); // 升级到普通读锁
            try {
                current = root;
                for (int i = 1; i < parts.length; i++) {
                    if (parts[i].isEmpty()) continue;
                    current = current.getChildren().get(parts[i]);
                    if (current == null) {
                        return null;
                    }
                }
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return current;
    }

    // 删除节点方法，欠缺监听器的实现
    public void removeNode(String parentPrincipal, String nodePath) {
        boolean removed = false;
        long stamp = lock.writeLock();
        try {
            String[] parts = nodePath.split("/");
            DataNode current = root;

            for (int i = 1; i < parts.length - 1; i++) {
                if (parts[i].isEmpty()) continue;
                current = current.getChildren().get(parts[i]);
                if (current == null) {
                    throw new IllegalArgumentException("Path does not exist: " + nodePath);
                }
            }
            String nodeName = parts[parts.length - 1];
            current.removeChild(parentPrincipal, nodeName);
            removed = true;
        } finally {
            lock.unlockWrite(stamp);
        }
        //节点删除的逻辑应该怎么设计：应该通知谁？
        /////////////////
    }


    // 注册一个监听事件
    public void addListener(DataNodeListener listener) {
        //使用了copyonwrite，CAS乐观并发思想，不需阻塞锁
        this.listeners.add(listener);

    }

    public void removeListener(DataNodeListener listener) {
        listeners.remove(listener);
    }

    // 通知事件监听器
    protected void notifyListeners(DataNode node, EventType eventType) {
        for (DataNodeListener listener : listeners) {
            if (listener.isInterestedIn(eventType)) {
                //每一个关心这个事件的节点都收到了通知
                listener.onDataChanged(node, eventType);
            }
        }

    }


    public void printTree() {
        printTreeNode(root, 0);
    }

    private void printTreeNode(DataNode node, int level) {
        if (node == null) return;

        // 创建缩进字符串，表示层级
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("|  "); // 使用 "|" 作为层级的占位符
        }
        // 打印当前节点及其深度（缩进表示）
        System.out.println(indent + (level > 0 ? "- " : "") + "Data: " + node.getData() + ", Permissions: " + node.getAcl() + ", Stat: " + node.getStat());
        // 递归打印每个子节点
        for (Map.Entry<String, DataNode> entry : node.getChildren().entrySet()) {
            // 打印子节点的键（名称）
            System.out.println(indent + "|-- Child Key: " + entry.getKey()); // 使用 "|--" 作为子节点的占位符
            // 递归打印子节点
            printTreeNode(entry.getValue(), level + 1);
        }
    }


}

