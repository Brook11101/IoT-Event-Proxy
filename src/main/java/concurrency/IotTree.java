package concurrency;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class IotTree {
    private RootNode rootNode = new RootNode();

    public void createDevice(String deviceName, UUID deviceUUID) {
        DeviceNode device = new DeviceNode(rootNode, deviceUUID, deviceName);
        rootNode.addDeviceNode(device);
    }

    public void createTask(String taskName, Set<UUID> triggerDevices, Set<UUID> actionDevices, TaskNode.ExecFunc execFunc) {
        Thread taskThread = new Thread(() -> {
            TaskNode tskNode = new TaskNode(rootNode, UUID.randomUUID(), taskName, triggerDevices, actionDevices, execFunc);
            tskNode.runTask();
        });
        taskThread.start();
    }
}
