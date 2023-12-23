package dataTree;

public class Main {
    public static void main(String[] args) {
        DataTree tree = new DataTree();

        // 添加事件监听器
        tree.addListener(new ZNodeListener() {
            @Override
            public void onDataChanged(ZNode node) {
                System.out.println("Data changed in node: New data: " + node.getData());
            }
        });

        // 定义主体
        String admin = "admin";
        String user1 = "user1";
        String user2 = "user2";

        // 设置根节点权限
        ZNode rootNode = tree.findNode("/");
        if (rootNode != null) {
            rootNode.getAcl().addPermission(admin, Permission.WRITE);
            rootNode.getAcl().addPermission(admin, Permission.READ);
            rootNode.getAcl().addPermission(admin, Permission.DELETE);
            rootNode.getAcl().addPermission(user1, Permission.READ);
            rootNode.getAcl().addPermission(user2, Permission.READ);
        }

        // 添加节点并设置权限
        try {
            long currentTime = System.currentTimeMillis();
            StatPersisted stat = new StatPersisted(1, 1, currentTime, currentTime, 0, 0, admin);
            tree.addNode("/node1", "Initial Data for Node 1", admin, stat);

            // 设置新节点权限
            ZNode node1 = tree.findNode("/node1");
            if (node1 != null) {
                node1.getAcl().addPermission(admin, Permission.WRITE);
                node1.getAcl().addPermission(admin, Permission.READ);
                node1.getAcl().addPermission(admin, Permission.DELETE);
                node1.getAcl().addPermission(user2, Permission.READ);
            }
        } catch (SecurityException e) {
            System.out.println("Failed to add node: " + e.getMessage());
        }

        // 尝试以不同权限用户更新节点
        try {
            ZNode node = tree.findNode("/node1");
            if (node != null) {
                node.setData("User1 tries to update Node 1", user1);
            }
        } catch (SecurityException e) {
            System.out.println("Update failed: " + e.getMessage());
        }

        try {
            ZNode node = tree.findNode("/node1");
            if (node != null) {
                node.setData("Admin updates Node 1", admin);
            }
        } catch (SecurityException e) {
            System.out.println("Update failed: " + e.getMessage());
        }

        // 读取节点数据
        try {
            ZNode node = tree.findNode("/node1");
            if (node != null) {
                String data = (String) node.getData();
                System.out.println("Data read from node1: " + data);
            } else {
                System.out.println("Node /node1 does not exist.");
            }
        } catch (SecurityException e) {
            System.out.println("Read failed: " + e.getMessage());
        }

        // 删除节点测试
        try {
            tree.removeNode("/node1", admin);
            System.out.println("Node /node1 deleted successfully.");
        } catch (SecurityException e) {
            System.out.println("Failed to delete node: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to delete node: " + e.getMessage());
        }
    }
}
