package dataTree;

public interface DataNodeListener {
    boolean isInterestedIn(EventType eventType);
    void onDataChanged(DataNode node, EventType eventType);
}
