package dataTree;

public class DataChangeEvent {
    private final ZNode node;

    public DataChangeEvent(ZNode node) {
        this.node = node;
    }

    public ZNode getNode() {
        return node;
    }
}
