package concurrency;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class RootNode {
    private CopyOnWriteArraySet<DeviceNode> deviceNodes = new CopyOnWriteArraySet<>();

    public Set<DeviceNode> getDeviceNodes() {
        return deviceNodes;
    }

    public void addDeviceNode(DeviceNode d) {
        deviceNodes.add(d);
    }
}
