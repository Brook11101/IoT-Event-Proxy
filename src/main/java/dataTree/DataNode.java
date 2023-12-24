package dataTree;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.StampedLock;

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
    //节点访问并发控制，使用乐观锁进行读写优化（带stamp 先读再校验策略）
    private final StampedLock lock = new StampedLock();

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
        long stamp = lock.writeLock();
        try {
            if (this.acl.hasPermission(parentPrincipal, Permission.WRITE) && !children.containsKey(childName)) {
                children.put(childName, child);
                return true;
            }
            return false;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    // 移除子节点
    public boolean removeChild(String parentPrincipal, String childName) {
        long stamp = lock.writeLock();
        try {
            if (this.acl.hasPermission(parentPrincipal, Permission.DELETE) && children.containsKey(childName)) {
                children.remove(childName);
                return true;
            }
            return false;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public ConcurrentHashMap<String, DataNode> getChildren() {
        return children;
    }

    public Object getData() {
        long stamp = lock.tryOptimisticRead();
        Object currentData = data;
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                currentData = data;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        // 这里可以被实现浅拷贝，将值复制给一个对象返回，而不是直接返回私有对象的引用，从而保证线程安全。
        return currentData;
    }

    public boolean setData(String currentPrincipal, Object newData, StatPersisted currentStat) {
        long stamp = lock.writeLock();
        try {
            if (this.acl.hasPermission(currentPrincipal, Permission.WRITE)) {
                this.data = newData;
                this.stat = currentStat;
                return true;
            } else {
                throw new SecurityException("No user write permission to change data of this node: " + currentPrincipal);
            }
        } finally {
            lock.unlockWrite(stamp);
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

