package dataTree;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.List;
import java.util.ArrayList;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ZNode {
    private volatile Object data;
    //对于任何一个节点的子节点map来说，这是一个一维的结构
    private final ConcurrentHashMap<String, ZNode> children;
    private final ZNode parent;
    private final AccessControlList acl;
    private final ReentrantReadWriteLock lock;
    private final StatPersisted stat;

    //创建一个节点的时候必须指派其父亲节点
    public ZNode(Object data, ZNode parent, StatPersisted stat) {
        this.data = data;
        this.children = new ConcurrentHashMap<>();
        this.parent = parent;
        this.acl = new AccessControlList();
        //每一个节点有自己的lock
        this.lock = new ReentrantReadWriteLock();
        this.stat = stat;
    }

    // 添加子节点
    public void addChild(String childName, Object childData, String principal, StatPersisted stat) {
        lock.writeLock().lock();
        try {
            if (!this.acl.hasPermission(principal, Permission.WRITE)) {
                throw new SecurityException("No write permission for principal: " + principal);
            }
            ZNode child = new ZNode(childData, this, stat);
            //map里面的键值存的是child name
            //父子节点的路径也是用 / 的树状
            this.children.put(childName, child);
        } finally {
            lock.writeLock().unlock();
        }
    }

    // 移除子节点
    public void removeChild(String childName, String principal) {
        lock.writeLock().lock();
        try {
            if (!this.acl.hasPermission(principal, Permission.DELETE)) {
                throw new SecurityException("No delete permission for principal: " + principal);
            }
            this.children.remove(childName);
        } finally {
            lock.writeLock().unlock();
        }
    }

    // Getters and Setters
    public Object getData() {
        lock.readLock().lock();
        try { return data; }
        finally { lock.readLock().unlock(); }
    }

    public void setData(Object data, String principal) {
        lock.writeLock().lock();
        try {
            // 权限检查
            if (!this.acl.hasPermission(principal, Permission.WRITE)) {
                throw new SecurityException("No write permission for principal: " + principal);
            }

            // 设置数据
            this.data = data;

            // 更新StatPersisted实例
            long currentTime = System.currentTimeMillis();
            this.stat.setMtime(currentTime);
            this.stat.setDataVersion(this.stat.getDataVersion() + 1); // 假设数据版本号每次增加1

        } finally {
            lock.writeLock().unlock();
        }
    }

    public ConcurrentHashMap<String, ZNode> getChildren() {
        return children;
    }

    public ZNode getParent() {
        return parent;
    }

    public AccessControlList getAcl() {
        return acl;
    }

    public StatPersisted getStat() {
        return stat;
    }
}

