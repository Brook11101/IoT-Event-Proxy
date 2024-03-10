package znode.dataTree;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class StatPersisted {
    //使用atomic类，本质是基于CAS的乐观锁，能够实现原子性，可见性，有序性
    //节点创建id
    private final AtomicLong cnid;
    //节点修改id
    private final AtomicLong mnid;
    //节点创建时间
    private final AtomicLong ctime;
    //节点修改时间
    private final AtomicLong mtime;
    //节点数据版本
    private final AtomicInteger dataVersion;
    //节点access control list版本
    private final AtomicInteger aclVersion;

    // 构造函数
    public StatPersisted(long cnid, long mnid, long ctime, long mtime, int dataVersion, int aclVersion) {
        this.cnid = new AtomicLong(cnid);
        this.mnid = new AtomicLong(mnid);
        this.ctime = new AtomicLong(ctime);
        this.mtime = new AtomicLong(mtime);
        this.dataVersion = new AtomicInteger(dataVersion);
        this.aclVersion = new AtomicInteger(aclVersion);
    }

    // Getters and Setters 使用原子变量的方法
    public long getCnid() { return cnid.get(); }
    public void setCnid(long cnid) { this.cnid.set(cnid); }

    public long getMnid() { return mnid.get(); }
    public void setMnid(long mnid) { this.mnid.set(mnid); }

    public long getCtime() { return ctime.get(); }
    public void setCtime(long ctime) { this.ctime.set(ctime); }

    public long getMtime() { return mtime.get(); }
    public void setMtime(long mtime) { this.mtime.set(mtime); }

    public int getDataVersion() { return dataVersion.get(); }
    public void setDataVersion(int dataVersion) { this.dataVersion.set(dataVersion); }

    public int getAclVersion() { return aclVersion.get(); }
    public void setAclVersion(int aclVersion) { this.aclVersion.set(aclVersion); }

    @Override
    public String toString() {
        return "StatPersisted{" +
                "cnid=" + cnid.get() +
                ", mnid=" + mnid.get() +
                ", ctime=" + ctime.get() +
                ", mtime=" + mtime.get() +
                ", dataVersion=" + dataVersion.get() +
                ", aclVersion=" + aclVersion.get() +
                '}';
    }
}
