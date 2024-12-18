package concurrency;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DeviceNode {

    // 使用公平锁实现，同一个device lock下的task node保持FIFO顺序竞争
    // 这里使用了reentrantlock的read-write lock，提高代码的执行效率
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private RootNode root;

    private UUID deviceUUID;
    private String deviceName;

    // 该设备下的任务节点
    private CopyOnWriteArraySet<TaskNode> taskNodes = new CopyOnWriteArraySet<>();

    public DeviceNode(RootNode root, UUID deviceUUID, String deviceName) {
        this.root = root;
        this.deviceUUID = deviceUUID;
        this.deviceName = deviceName;
    }

    public UUID getDeviceUUID() {
        return deviceUUID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public ReentrantReadWriteLock.WriteLock getWriteLock() {
        return lock.writeLock();
    }

    public ReentrantReadWriteLock.ReadLock getReadLock() {
        return lock.readLock();
    }

    public Set<TaskNode> getTaskNodes() {
        return taskNodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceNode that = (DeviceNode) o;
        return Objects.equals(deviceUUID, that.deviceUUID) &&
                Objects.equals(deviceName, that.deviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceUUID, deviceName);
    }
}
