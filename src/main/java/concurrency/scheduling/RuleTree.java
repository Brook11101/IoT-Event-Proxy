package concurrency.scheduling;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class RuleTree {

    private RootNode rootNode = new RootNode();
    // 模拟真实 action 到达的概率
    private static final double TRUE_ACTION_ARRIVAL_PERCENT = 1;

    public void createDevice(String deviceName, UUID deviceUUID) {
        DeviceNode device = new DeviceNode(rootNode, deviceUUID, deviceName);
        rootNode.addDeviceNode(device);
    }

    /**
     * 创建任务：内部启动任务线程和监听线程，并返回这两个线程的集合。
     * @param taskName 任务名称
     * @param triggerDevices 触发设备集合
     * @param actionDevices 动作设备集合
     * @param execFunc 任务执行逻辑
     * @return 内部新建的线程列表（包括任务线程和监听线程）
     */
    public List<Thread> createTask(String taskName, TreeSet<UUID> triggerDevices, TreeSet<UUID> actionDevices, TaskNode.SimpleExecFunc execFunc) {
        List<Thread> threadList = new ArrayList<>();

        // 内部类封装任务及其 action 到达逻辑
        class TaskWithListener {
            private final Thread taskThread;
            private final Thread listenerThread;

            TaskWithListener(String taskName, TreeSet<UUID> triggerDevices, TreeSet<UUID> actionDevices, TaskNode.SimpleExecFunc execFunc) {
                final AtomicBoolean arrivalFlag = new AtomicBoolean(false);
                final AtomicBoolean timeWindowFlag = new AtomicBoolean(false);

                taskThread = new Thread(() -> {
                    TaskNode taskNode = new TaskNode(rootNode, UUID.randomUUID(), taskName, triggerDevices, actionDevices, execFunc);
                    // 开始执行任务
                    taskNode.runTask(arrivalFlag, timeWindowFlag);
                });

                listenerThread = new Thread(() -> {
                    try {
                        // 模拟 action 到达的延迟
                        Random random = new Random();
                        long randomDelay = random.nextInt(5) * 1000;
                        Thread.sleep(randomDelay);

                        boolean newStatus = Math.random() <= TRUE_ACTION_ARRIVAL_PERCENT;
                        arrivalFlag.set(newStatus);

                        // 如果 action 已到达，立即设置等待窗口结束标志并退出
                        if (arrivalFlag.get()) {
                            timeWindowFlag.set(true);
                            return;
                        }

                        // 如果 action 未到达，则等待剩余时间后结束等待窗口
                        long remainingTime = 5000 - randomDelay;
                        Thread.sleep(remainingTime);
                        timeWindowFlag.set(true);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }

            void start() {
                taskThread.start();
                // 防止 listenerThread 先于任务线程执行
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                listenerThread.start();
            }

            List<Thread> getThreads() {
                return Arrays.asList(taskThread, listenerThread);
            }
        }

        TaskWithListener taskWithListener = new TaskWithListener(taskName, triggerDevices, actionDevices, execFunc);
        taskWithListener.start();
        threadList.addAll(taskWithListener.getThreads());
        return threadList;
    }
}
