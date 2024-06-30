package concurrency;

import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class IotTree {
    private RootNode rootNode = new RootNode();
    private static final double TRUE_ACTION_COME_PERCENT = 0.1;

    public void createDevice(String deviceName, UUID deviceUUID) {
        DeviceNode device = new DeviceNode(rootNode, deviceUUID, deviceName);
        rootNode.addDeviceNode(device);
    }

//    public void createTask(String taskName, Set<UUID> triggerDevices, Set<UUID> actionDevices, TaskNode.ExecFunc execFunc) {
//        Thread taskThread = new Thread(() -> {
//            TaskNode tskNode = new TaskNode(rootNode, UUID.randomUUID(), taskName, triggerDevices, actionDevices, execFunc);
//            tskNode.runTask();
//        });
//        taskThread.start();
//    }

    public void createTask(String taskName, Set<UUID> triggerDevices, Set<UUID> actionDevices, TaskNode.ExecFunc execFunc) {
        // 使用内部类封装每个任务及其打断监控逻辑
        class TaskWithMonitor {
            private final Thread taskThread;
            private final Thread interruptMonitor;

            TaskWithMonitor(String taskName, Set<UUID> triggerDevices, Set<UUID> actionDevices, TaskNode.ExecFunc execFunc) {
                final AtomicBoolean isSleeping = new AtomicBoolean(false);
                final AtomicBoolean isExecuting = new AtomicBoolean(true);

                taskThread = new Thread(() -> {
                    try {
                        TaskNode tskNode = new TaskNode(rootNode, UUID.randomUUID(), taskName, triggerDevices, actionDevices, execFunc);
                        tskNode.runTask(isSleeping, isExecuting);
                    } finally {
                        isSleeping.set(false);
                        isExecuting.set(false);
                    }
                });

                interruptMonitor = new Thread(() -> {
                    Random random = new Random();
                    while (isExecuting.equals(true)) {

                        if (isSleeping.get() && random.nextDouble() < TRUE_ACTION_COME_PERCENT) {
                            System.out.println("模拟真实action到来，打断睡眠等待");
                            taskThread.interrupt();
                            break;
                        }
                        try {
                            Thread.sleep(100); // 间隔检查
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                });
            }

            void start() {
                taskThread.start();
                interruptMonitor.start();
            }
        }

        TaskWithMonitor taskWithMonitor = new TaskWithMonitor(taskName, triggerDevices, actionDevices, execFunc);
        taskWithMonitor.start();
    }
}
