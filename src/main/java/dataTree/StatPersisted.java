package dataTree;

public class StatPersisted {
    //节点创建id
    private volatile long cnid;
    //节点修改id
    private volatile long mnid;
    //节点创建时间
    private volatile long ctime;
    //节点修改时间
    private volatile long mtime;
    //节点数据版本
    private volatile int dataVersion;
    //节点access control list版本
    private volatile int aclVersion;

    // 构造函数
    public StatPersisted(long cnid, long mnid, long ctime, long mtime, int dataVersion, int aclVersion) {
        this.cnid = cnid;
        this.mnid = mnid;
        this.ctime = ctime;
        this.mtime = mtime;
        this.dataVersion = dataVersion;
        this.aclVersion = aclVersion;
    }

    // Getters and Setters
    public long getCnid() { return cnid; }
    public void setCnid(long cnid) { this.cnid = cnid; }

    public long getMnid() { return mnid; }
    public void setMnid(long mnid) { this.mnid = mnid; }

    public long getCtime() { return ctime; }
    public void setCtime(long ctime) { this.ctime = ctime; }

    public long getMtime() { return mtime; }
    public void setMtime(long mtime) { this.mtime = mtime; }

    public int getDataVersion() { return dataVersion; }
    public void setDataVersion(int dataVersion) { this.dataVersion = dataVersion; }

    public int getAclVersion() { return aclVersion; }
    public void setAclVersion(int aclVersion) { this.aclVersion = aclVersion; }

    @Override
    public String toString() {
        return "StatPersisted{" +
                "cnid=" + cnid +
                ", mnid=" + mnid +
                ", ctime=" + ctime +
                ", mtime=" + mtime +
                ", dataVersion=" + dataVersion +
                ", aclVersion=" + aclVersion +
                '}';
    }
}
