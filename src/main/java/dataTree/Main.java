package dataTree;

public class Main {
    public static void main(String[] args) {
        DataTree tree = new DataTree();

        // 创建Watcher实例
        Watcher nodeCreationWatcher = new Watcher("NodeCreation", EventType.NODE_CREATED);
        Watcher nodeDeletionWatcher = new Watcher("NodeDeletion", EventType.NODE_DELETED);
        Watcher nodeModificationWatcher = new Watcher("NodeDataChange", EventType.NODE_DATA_CHANGED);
        Watcher nodeChildrenModificationWatcher = new Watcher("NodeChildrenChange", EventType.NODE_CHILDREN_CHANGED);

        // 定义主体
        String admin = "admin";

        // 设置根节点权限
        DataNode rootNode = tree.findNode("/");
        if (rootNode != null) {
            rootNode.getAcl().addPermission(admin, Permission.WRITE);
            rootNode.getAcl().addPermission(admin, Permission.READ);
            rootNode.getAcl().addPermission(admin, Permission.DELETE);

            // 为根节点添加Watcher
            tree.watchData("/", nodeCreationWatcher);
            tree.watchData("/", nodeDeletionWatcher);
            tree.watchData("/", nodeModificationWatcher);
            tree.watchChildren("/", nodeChildrenModificationWatcher);

        }

        // 添加节点并设置权限
        try {
            long currentTime = System.currentTimeMillis();
            StatPersisted stat = new StatPersisted(1, 1, currentTime, currentTime, 0, 0);
            tree.addNode(admin, "/node1", "Node 1 Data", stat);
            tree.watchChildren("/node1", nodeChildrenModificationWatcher);
            tree.addNode(admin, "/node1/node2", "Node 2 Data", stat);
            tree.addNode(admin, "/node3", "Node 3 Data", stat);
            tree.watchChildren("/node3", nodeChildrenModificationWatcher);
            tree.watchData("/node1/node2",nodeDeletionWatcher);
            tree.addNode(admin, "/node3/node4", "Node 4 Data", stat);
            tree.removeNode(admin, "/node1/node2");
            tree.modifyNode(admin,"/","new root",stat);

            tree.printTree();

        } catch (SecurityException e) {
            System.out.println("Failed to add node: " + e.getMessage());
        }
    }
}
