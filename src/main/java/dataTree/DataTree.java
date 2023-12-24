package dataTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataTree {
    private final DataNode root;
    private final List<DataNodeListener> listeners;
    private final ReentrantReadWriteLock lock;

    public DataTree() {
        //创建根节点，生成根节点的信息
        long currentTime = System.currentTimeMillis();
        StatPersisted rootStat = new StatPersisted(0, 0, currentTime, currentTime, 0, 0);
        this.root = new DataNode(null, "root", rootStat);
        this.listeners = new ArrayList<>();
        this.lock = new ReentrantReadWriteLock();
    }

    //为树结构添加节点
    public DataNode addNode(String parentPrincipal, String nodePath, Object nodeData, StatPersisted nodeStat) {
        lock.writeLock().lock();
        try {
            //按照分隔符沿树干遍历
            String[] parts = nodePath.split("/");
            DataNode current = root;

            for (int i = 1; i < parts.length; i++) {
                if (parts[i].isEmpty()) continue;
                DataNode child = current.getChildren().get(parts[i]);
                if (child == null) {
                    child = new DataNode(current, nodeData, nodeStat);
                    current.addChild(parentPrincipal, parts[i], child);
                }
                current = child;
            }
            //认为创建这个子节点的线程默认拥有其所有权限
            current.getAcl().addPermission(parentPrincipal, Permission.WRITE);
            current.getAcl().addPermission(parentPrincipal, Permission.READ);
            current.getAcl().addPermission(parentPrincipal, Permission.DELETE);
            //设置数据
            current.setData(parentPrincipal, nodeData, nodeStat);
            //通知节点变化
            notifyListeners(new DataChangeEvent(current));

            return current;
        } finally {
            lock.writeLock().unlock();
        }
    }


    // 查找节点方法
    public DataNode findNode(String nodePath) {
        lock.readLock().lock();
        try {
            String[] parts = nodePath.split("/");
            DataNode current = root;

            for (int i = 1; i < parts.length; i++) {
                if (parts[i].isEmpty()) continue;
                current = current.getChildren().get(parts[i]);
                if (current == null) {
                    return null;
                }
            }
            return current;
        } finally {
            lock.readLock().unlock();
        }
    }

    // 删除节点方法，欠缺监听器的实现
    public void removeNode(String parentPrincipal, String nodePath) {
        lock.writeLock().lock();
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
        } finally {
            lock.writeLock().unlock();
        }
    }


    // 注册一个监听事件
    public void addListener(DataNodeListener listener) {
        lock.writeLock().lock();
        try {
            this.listeners.add(listener);
        } finally {
            lock.writeLock().unlock();
        }
    }

    // 通知事件监听器
    protected void notifyListeners(DataChangeEvent event) {
        lock.readLock().lock();
        try {
            for (DataNodeListener listener : listeners) {
                //这里是所有的Listener都会收到这一变化的通知
                listener.onDataChanged(event.getNode());
            }
        } finally {
            lock.readLock().unlock();
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

