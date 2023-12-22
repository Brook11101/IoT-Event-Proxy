package dataTree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataTree {
    private final ZNode root;
    private final List<ZNodeListener> listeners;
    private final ReentrantReadWriteLock lock;

    public DataTree() {
        this.root = new ZNode("root", null, null);
        this.listeners = new ArrayList<>();
        this.lock = new ReentrantReadWriteLock();
    }

    public void addNode(String path, Object data, String principal) {
        lock.writeLock().lock();
        try {
            String[] parts = path.split("/");
            ZNode current = root;

            for (String part : parts) {
                if (part.isEmpty()) continue;
                //这里就是沿着path一条一条往下过
                ZNode child = current.getChild(part, principal);
                if (child == null) {
                    current.addChild(part, null, principal);  // 添加子节点
                    child = current.getChild(part, principal); // 获取新创建的子节点

                    // 为新节点设置权限
                    if (child != null) {
                        child.getAcl().addPermission(principal, Permission.WRITE);
                        child.getAcl().addPermission(principal, Permission.READ);
                        // 这里可能还需要添加其他用户的权限
                    }
                }
                current = child;
            }

            if (current != null) {
                current.setData(principal, data); // 设置数据
                notifyListeners(new DataChangeEvent(current));
            }
        } finally {
            lock.writeLock().unlock();
        }
    }


    public ZNode findNode(String path, String principal) {
        lock.readLock().lock();
        try {
            String[] parts = path.split("/");
            ZNode current = root;

            for (String part : parts) {
                if (part.isEmpty()) continue;
                current = current.getChild(part, principal);
                if (current == null) {
                    return null;
                }
            }

            return current;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void addListener(ZNodeListener listener) {
        lock.writeLock().lock();
        try {
            this.listeners.add(listener);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void removeNode(String path, String principal) {
        lock.writeLock().lock();
        try {
            String[] parts = path.split("/");
            ZNode current = root;

            for (int i = 0; i < parts.length - 1; i++) {
                String part = parts[i];
                if (part.isEmpty()) continue;
                current = current.getChild(part, principal);
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

