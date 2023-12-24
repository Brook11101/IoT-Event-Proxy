package dataTree;

public class Main {
    public static void main(String[] args) {
        DataTree tree = new DataTree();

        // 添加事件监听器
        tree.addListener(new DataNodeListener() {
            @Override
            public boolean isInterestedIn(EventType eventType) {
                // 仅为示例，这里监听所有类型的事件
                return true;
            }

            @Override
            public void onDataChanged(DataNode node, EventType eventType) {
                switch (eventType) {
                    case NODE_CREATED:
                        System.out.println("Node created: " + node.getData());
                        break;
                    case NODE_DELETED:
                        System.out.println("Node deleted");
                        break;
                    case NODE_MODIFIED:
                        System.out.println("Node modified: " + node.getData());
                        break;
                }
            }
        });

        // 定义主体
        String admin = "admin";

        // 设置根节点权限
        DataNode rootNode = tree.findNode("/");
        if (rootNode != null) {
            rootNode.getAcl().addPermission(admin, Permission.WRITE);
            rootNode.getAcl().addPermission(admin, Permission.READ);
            rootNode.getAcl().addPermission(admin, Permission.DELETE);
        }

        // 添加节点并设置权限
        try {
            long currentTime = System.currentTimeMillis();
            StatPersisted stat = new StatPersisted(1, 1, currentTime, currentTime, 0, 0);
            tree.addNode(admin, "/node1", "Node 1 Data", stat);
            tree.addNode(admin, "/node1/node2", "Node 2 Data", stat);
            tree.addNode(admin, "/node3", "Node 3 Data", stat);
            tree.addNode(admin, "/node3/node4", "Node 4 Data", stat);

            tree.modifyNode(admin, "/node1/node2", "new Node 2 Data", stat);

            tree.printTree();

        } catch (SecurityException e) {
            System.out.println("Failed to add node: " + e.getMessage());
        }


    }
}
