package dataTree;

public class Main {
    public static void main(String[] args) {
        DataTree tree = new DataTree();

        // 添加事件监听器
        tree.addListener(new DataNodeListener() {
            @Override
            public void onDataChanged(DataNode node) {
                System.out.println("Data changed in node: New data: " + node.getData());
            }
        });

        // 定义主体
        String admin = "admin";
        String user1 = "user1";

        // 设置根节点权限
        DataNode rootNode = tree.findNode("/");
        if (rootNode != null) {
            rootNode.getAcl().addPermission(admin, Permission.WRITE);
            rootNode.getAcl().addPermission(admin, Permission.READ);
            rootNode.getAcl().addPermission(admin, Permission.DELETE);
            rootNode.getAcl().addPermission(user1, Permission.READ);
        }

        // 添加节点并设置权限
        try {
            long currentTime = System.currentTimeMillis();
            StatPersisted stat = new StatPersisted(1, 1, currentTime, currentTime, 0, 0);
            tree.addNode(admin, "/node1", "Node 1 Data", stat);
            tree.addNode(admin, "/node1/node2", "Node 2 Data", stat);
            tree.addNode(admin, "/node3", "Node 3 Data", stat);
            tree.addNode(admin, "/node3/node4", "Node 4 Data", stat);

            tree.printTree();

        } catch (SecurityException e) {
            System.out.println("Failed to add node: " + e.getMessage());
        }


    }
}
