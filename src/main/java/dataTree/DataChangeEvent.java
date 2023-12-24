package dataTree;

public class DataChangeEvent {
    private final DataNode node;

    public DataChangeEvent(DataNode node) {
        this.node = node;
    }

    public DataNode getNode() {
        return node;
    }
}
