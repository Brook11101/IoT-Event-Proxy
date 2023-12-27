package dataTree;

public class Main {
    public static void main(String[] args) {
        DataTree tree = new DataTree();

        // 创建Watcher实例
        Watcher nodeCreationWatcher = new Watcher("NodeCreation");
        Watcher nodeDeletionWatcher = new Watcher("NodeDeletion");
        Watcher nodeModificationWatcher = new Watcher("NodeModification");

        // 定义主体
        String admin = "admin";

        // 设置根节点权限
        DataNode rootNode = tree.findNode("/");
        if (rootNode != null) {
            rootNode.getAcl().addPermission(admin, Permission.WRITE);
            rootNode.getAcl().addPermission(admin, Permission.READ);
            rootNode.getAcl().addPermission(admin, Permission.DELETE);

            // 为根节点添加Watcher
            tree.addWatcher("/", nodeCreationWatcher);
            tree.addWatcher("/", nodeDeletionWatcher);
            tree.addWatcher("/", nodeModificationWatcher);
        }

        // 添加节点并设置权限
        try {
            long currentTime = System.currentTimeMillis();
            StatPersisted stat = new StatPersisted(1, 1, currentTime, currentTime, 0, 0);
            tree.addNode(admin, "/node1", "Node 1 Data", stat);
            tree.addNode(admin, "/node1/node2", "Node 2 Data", stat);
            tree.addNode(admin, "/node3", "Node 3 Data", stat);
            tree.addNode(admin, "/node3/node4", "Node 4 Data", stat);
            tree.addWatcher("/node1/node2", nodeModificationWatcher);
            tree.modifyNode(admin, "/node1/node2", "new Node 2 Data", stat);

            tree.printTree();

        } catch (SecurityException e) {
            System.out.println("Failed to add node: " + e.getMessage());
        }
    }
}
