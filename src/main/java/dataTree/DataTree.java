package dataTree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataTree {
    private final ZNode root;
    private final List<ZNodeListener> listeners;
    private final ReentrantReadWriteLock lock;

    public DataTree() {
        long currentTime = System.currentTimeMillis();
        //创建根节点，生成根节点的信息
        StatPersisted rootStat = new StatPersisted(0, 0, currentTime, currentTime, 0, 0, "anyone");
        this.root = new ZNode(null, null, rootStat);
        this.listeners = new ArrayList<>();
        this.lock = new ReentrantReadWriteLock();
    }

    // 添加节点方法
    public void addNode(String path, Object data, String principal, StatPersisted stat) {
        //一棵树上的所有节点共用了一把锁
        lock.writeLock().lock();
        try {
            String[] parts = path.split("/");
            ZNode current = root;

            for (int i = 1; i < parts.length; i++) {
                if (parts[i].isEmpty()) continue;
                ZNode child = current.getChildren().get(parts[i]);
                if (child == null) {
                    if (!current.getAcl().hasPermission(principal, Permission.WRITE)) {
                        throw new SecurityException("No write permission for principal: " + principal);
                    }

                    child = new ZNode(data, current, stat);
                    current.addChild(parts[i], data, principal, stat);
                }
                current = child;
            }
            //这里增加了一项设置，认为创建这个节点的线程默认拥有这个节点的所有权限
            current.getAcl().addPermission(principal,Permission.WRITE);
            current.getAcl().addPermission(principal,Permission.READ);

            current.setData(data,principal);
            notifyListeners(new DataChangeEvent(current));
        } finally {
            lock.writeLock().unlock();
        }
    }



    // 查找节点方法
    public ZNode findNode(String path) {
        lock.readLock().lock();
        try {
            String[] parts = path.split("/");
            ZNode current = root;

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
    public void removeNode(String path, String principal) {
        lock.writeLock().lock();
        try {
            String[] parts = path.split("/");
            ZNode current = root;

            for (int i = 1; i < parts.length - 1; i++) {
                if (parts[i].isEmpty()) continue;
                current = current.getChildren().get(parts[i]);
                if (current == null) {
                    throw new IllegalArgumentException("Path does not exist: " + path);
                }
            }

            String nodeName = parts[parts.length - 1];
            current.removeChild(nodeName, principal);
        } finally {
            lock.writeLock().unlock();
        }
    }

    // 添加事件监听器方法
    public void addListener(ZNodeListener listener) {
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
            for (ZNodeListener listener : listeners) {
                listener.onDataChanged(event.getNode());
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    // ... 其他方法
}

