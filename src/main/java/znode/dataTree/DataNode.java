package znode.dataTree;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.ConcurrentHashMap;

public class DataNode {
    //节点父亲
    private final DataNode parent;
    //节点存储的数据
    private volatile Object data;
    //节点的信息维护类
    private volatile StatPersisted stat;
    //节点的子节点维护map：一维子节点
    private final ConcurrentHashMap<String, DataNode> children;
    //节点访问控制列表
    private final AccessControlList acl;
    //节点访问并发控制，这里使用可重入锁，便于减少多线程时死锁
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    //创建一个节点的时候必须指派其父亲节点
    public DataNode(DataNode parent, Object data, StatPersisted stat) {
        this.parent = parent;
        this.data = data;
        this.stat = stat;
        this.children = new ConcurrentHashMap<>();
        this.acl = new AccessControlList();
    }

    // 添加子节点
    public boolean addChild(String parentPrincipal, String childName, DataNode child) {
        rwLock.writeLock().lock();
        try {
            if (this.acl.hasPermission(parentPrincipal, Permission.WRITE) && !children.containsKey(childName)) {
                children.put(childName, child);
                return true;
            }
            return false;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    // 移除子节点
    public boolean removeChild(String parentPrincipal, String childName) {
        rwLock.writeLock().lock();
        try {
            if (this.acl.hasPermission(parentPrincipal, Permission.DELETE) && children.containsKey(childName)) {
                children.remove(childName);
                return true;
            }
            return false;
        } finally {
            rwLock.writeLock().lock();
        }
    }

    public ConcurrentHashMap<String, DataNode> getChildren() {
        return children;
    }

    public Object getData() {
        // 如果当前线程已经持有写锁，则直接返回数据，避免获取读锁
        if (rwLock.isWriteLockedByCurrentThread()) {
            return data;
        }

        rwLock.readLock().lock();
        try {
            return data;
        } finally {
            rwLock.readLock().unlock();
        }
    }


    public boolean setData(String currentPrincipal, Object newData, StatPersisted currentStat) {
        rwLock.writeLock().lock();
        try {
            if (this.acl.hasPermission(currentPrincipal, Permission.WRITE)) {
                this.data = newData;
                this.stat = currentStat;
                return true;
            } else {
                throw new SecurityException("No user write permission to change data of this node: " + currentPrincipal);
            }
        } finally {
            rwLock.writeLock().unlock();
        }
    }


    public DataNode getParent() {
        return parent;
    }

    public AccessControlList getAcl() {
        return acl;
    }

    public StatPersisted getStat() {
        return stat;
    }
}

