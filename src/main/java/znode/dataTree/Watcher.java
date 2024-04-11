package znode.dataTree;

public class Watcher {
    private String describe; // Watcher的描述
    private EventType eventType; // Watcher关注的事件类型

    public Watcher(String describe, EventType eventType) {
        this.describe = describe;
        this.eventType = eventType;
    }

    public String getDescribe() {
        return describe;
    }

    public EventType getEventType() {
        return eventType;
    }

    // 处理特定事件的方法
    public void onEvent(EventType eventType, DataNode node) {
        if (this.eventType != eventType) {
            return; // 如果事件类型不匹配，则不处理
        }

        switch (eventType) {
            case NODE_CREATED:
                handleNodeCreated(node);
                break;
            case NODE_DATA_CHANGED:
                handleNodeDataChanged(node);
                break;
            case NODE_DELETED:
                handleNodeDeleted();
                break;
            case NODE_CHILDREN_CHANGED:
                handleNodeChildrenChanged(node);
                break;
            default:
                System.out.println("Unhandled event type: " + eventType);
                break;
        }
    }

    // 以下是具体的事件处理方法，这里可以根据需要实现逻辑
    private void handleNodeCreated(DataNode node) {
        System.out.println("Watcher '" + describe + "' notified of node creation.  Node Data: " + node.getData());
    }

    private void handleNodeDataChanged(DataNode node) {
        System.out.println("Watcher '" + describe + "' notified of node data change.   Node Data: " + node.getData());
    }

    private void handleNodeDeleted() {
        System.out.println("Watcher '" + describe + "' notified of node deletion.");
    }

    private void handleNodeChildrenChanged(DataNode node) {
        if (node == null) {
            System.out.println("Watcher '" + describe + "' notified of children change. Delete Child Node");
        } else
            System.out.println("Watcher '" + describe + "' notified of children change.  Child Node Data: " + node.getData());
    }
}
