package concurrency;

import java.util.*;

import static java.lang.Thread.sleep;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void main(String[] args) {
        IotTree tree = new IotTree();

        UUID TestDevice1 = UUID.randomUUID();
        tree.createDevice("test device 1", TestDevice1);

        UUID TestDevice2 = UUID.randomUUID();
        tree.createDevice("test device 2", TestDevice2);

        UUID TestDevice3 = UUID.randomUUID();
        tree.createDevice("test device 3", TestDevice3);

        Set<UUID> testTask1Triggers = new HashSet<>();
        Set<UUID> testTask1Actions = new HashSet<>();
        testTask1Triggers.add(TestDevice1);
        testTask1Actions.add(TestDevice2);
        testTask1Actions.add(TestDevice3);
        tree.createTask("test task 1", testTask1Triggers, testTask1Actions, () -> sleep(10000));

        try {
            sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Set<UUID> testTask2Triggers = new HashSet<>();
        testTask2Triggers.add(TestDevice2);
        Set<UUID> testTask2Actions = new HashSet<>();
        testTask2Actions.add(TestDevice3);
        tree.createTask("test task 2", testTask2Triggers, testTask2Actions, () -> sleep(2000));

//        try {
//            sleep(200);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Set<UUID> testTask3Triggers = new HashSet<>();
//        testTask3Triggers.add(TestDevice1);
//        testTask3Triggers.add(TestDevice2);
//        Set<UUID> testTask3Actions = new HashSet<>();
//        testTask3Actions.add(TestDevice3);
//        tree.createTask("test task 3", testTask3Triggers, testTask3Actions, () -> sleep(3000));

    }
}