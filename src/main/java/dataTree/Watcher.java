package dataTree;

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
    public void onEvent(EventType eventType) {
        if (this.eventType != eventType) {
            return; // 如果事件类型不匹配，则不处理
        }

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
            case NODE_CHILDREN_CHANGED:
                handleNodeChildrenChanged();
                break;
            default:
                System.out.println("Unhandled event type: " + eventType);
                break;
        }
    }

    // 以下是具体的事件处理方法，这里可以根据需要实现逻辑
    private void handleNodeCreated() {
        System.out.println("Watcher '" + describe + "' notified of node creation.");
    }

    private void handleNodeDataChanged() {
        System.out.println("Watcher '" + describe + "' notified of node data change.");
    }

    private void handleNodeDeleted() {
        System.out.println("Watcher '" + describe + "' notified of node deletion.");
    }

    private void handleNodeChildrenChanged() {
        System.out.println("Watcher '" + describe + "' notified of children change.");
    }
}
