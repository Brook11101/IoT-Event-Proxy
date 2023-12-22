package dataTree;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ZNode {
    private final String name;
    private Object data;
    private int version;
    private final Map<String, ZNode> children;
    private final ZNode parent;
    private final AccessControlList acl;
    private final ReentrantReadWriteLock lock;

    public ZNode(String name, Object data, ZNode parent) {
        this.name = name;
        this.data = data;
        this.version = 0;
        this.children = new HashMap<>();
        this.parent = parent;
        this.acl = new AccessControlList();
        this.lock = new ReentrantReadWriteLock();
    }

    public void setData(String principal, Object data) {
        lock.writeLock().lock();
        try {
            if (this.acl.hasPermission(principal, Permission.WRITE)) {
                this.data = data;
                this.version++;
                System.out.println("New data has been set");
            } else {
                throw new SecurityException("No write permission for principal: " + principal);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Object getData(String principal) {
        lock.readLock().lock();
        try {
            if (this.acl.hasPermission(principal, Permission.READ)) {
                return data;
            } else {
                throw new SecurityException("No read permission for principal: " + principal);
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    public void addChild(String childName, Object childData, String principal) {
        lock.writeLock().lock();
        try {
            if (this.acl.hasPermission(principal, Permission.WRITE)) {
                ZNode childNode = new ZNode(childName, childData, this);
                this.children.put(childName, childNode);
            } else {
                throw new SecurityException("No write permission for principal: " + principal);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public ZNode getChild(String childName, String principal) {
        lock.readLock().lock();
        try {
            if (this.acl.hasPermission(principal, Permission.READ)) {
                return children.get(childName);
            } else {
                throw new SecurityException("No read permission for principal: " + principal);
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getVersion() {
        lock.readLock().lock();
        try {
            return version;
        } finally {
            lock.readLock().unlock();
        }
    }

    public AccessControlList getAcl() {
        return acl;
    }

    public void removeChild(String childName, String principal) {
        lock.writeLock().lock();
        try {
            if (this.acl.hasPermission(principal, Permission.DELETE)) {
                this.children.remove(childName);
            } else {
                throw new SecurityException("No delete permission for principal: " + principal);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
}
