package concurrency;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DeviceNode {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private RootNode root;
    private UUID deviceUUID;
    private String deviceName;
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

    public ReentrantReadWriteLock getLock() {
        return lock;
    }

    public Set<TaskNode> getTaskNodes() {
        return taskNodes;
    }

}
