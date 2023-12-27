package dataTree;

public class Watcher {
    // Watcher的类型或描述
    private String type;

    public Watcher(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    // 处理特定事件的方法
    public void onEvent(EventType eventType) {
        switch (eventType) {
            case NODE_CREATED:
                handleNodeCreated();
                break;
            case NODE_DATA_CHANGED:
                handleNodeDataChanged();
                break;
            case NODE_DELETED:
                handleNodeDeleted();
                break;
            default:
                System.out.println("Unhandled event type: " + eventType);
                break;
        }
    }

    private void handleNodeCreated() {
        System.out.println("Watcher '" + type + "' notified of node creation.");
        // 实现节点创建时的具体逻辑
    }

    private void handleNodeDataChanged() {
        System.out.println("Watcher '" + type + "' notified of node data change.");
        // 实现节点数据变化时的具体逻辑
    }

    private void handleNodeDeleted() {
        System.out.println("Watcher '" + type + "' notified of node deletion.");
        // 实现节点删除时的具体逻辑
    }
}
