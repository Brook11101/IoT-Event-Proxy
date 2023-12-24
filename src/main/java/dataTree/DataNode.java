package dataTree;

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
    //节点访问并发控制
    private final ReentrantReadWriteLock lock;

    //创建一个节点的时候必须指派其父亲节点
    public DataNode(DataNode parent, Object data, StatPersisted stat) {
        this.parent = parent;
        this.data = data;
        this.stat = stat;
        this.children = new ConcurrentHashMap<>();
        this.acl = new AccessControlList();
        this.lock = new ReentrantReadWriteLock();
    }

    // 添加子节点
    public void addChild(String parentPrincipal, String childName, DataNode child) {
        lock.writeLock().lock();
        try {
            if (!this.acl.hasPermission(parentPrincipal, Permission.WRITE)) {
                throw new SecurityException("No user write permission to create child of this node: " + parentPrincipal);
            }
            this.children.put(childName, child);
        } finally {
            lock.writeLock().unlock();
        }
    }

    // 移除子节点
    public void removeChild(String parentPrincipal, String childName) {
        lock.writeLock().lock();
        try {
            if (!this.acl.hasPermission(parentPrincipal, Permission.DELETE)) {
                throw new SecurityException("No user remove permission to delete child of this node: " + parentPrincipal);
            }
            this.children.remove(childName);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public ConcurrentHashMap<String, DataNode> getChildren() {
        return children;
    }

    public Object getData() {
        lock.readLock().lock();
        try {
            return data;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void setData(String currentPrincipal, Object data, StatPersisted currentStat) {
        lock.writeLock().lock();
        try {
            // 权限检查
            if (!this.acl.hasPermission(currentPrincipal, Permission.WRITE)) {
                throw new SecurityException("No user write permission to change data of this node: " + currentPrincipal);
            }
            // 设置数据
            this.data = data;
            // 更新StatPersisted实例
            this.stat = currentStat;

        } finally {
            lock.writeLock().unlock();
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

