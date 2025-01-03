package concurrency.scheduling;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class RuleTree {

    private RootNode rootNode = new RootNode();
    // 用于模拟真实 action 到达，虚拟 taskNode 结束等待窗口，升级为真实 taskNode
    private static final double TRUE_ACTION_ARRIVAL_PERCENT = 2;


    public void createDevice(String deviceName, UUID deviceUUID) {
        DeviceNode device = new DeviceNode(rootNode, deviceUUID, deviceName);
        rootNode.addDeviceNode(device);
    }

    // 添加虚拟 TaskNode，并使用时间窗口
    // 参数说明：该规则名，规则的 trigger 设备，规则的 action 设备，规则的执行动作
    public void createTask(String taskName, TreeSet<UUID> triggerDevices, TreeSet<UUID> actionDevices, TaskNode.SimpleExecFunc execFunc) {

        // 使用内部类封装任务及其action到达逻辑
        class TaskWithListener {
            private final Thread taskThread;
            private final Thread listenerThread;

            //任务线程和通知线程应该是独立的，任务线程应该先启动，最多等待五分钟。
            //通知线程可以在5min的时间窗口内任意一个点去打断等待，或者不打断。

            TaskWithListener(String taskName, TreeSet<UUID> triggerDevices, TreeSet<UUID> actionDevices, TaskNode.SimpleExecFunc execFunc) {
                final AtomicBoolean arrivalFlag = new AtomicBoolean(false);
                final AtomicBoolean timeWindowFlag = new AtomicBoolean(false);

                taskThread = new Thread(() -> {
                    TaskNode taskNode = new TaskNode(rootNode, UUID.randomUUID(), taskName, triggerDevices, actionDevices, execFunc);
                    //开始执行任务
                    taskNode.runTask(arrivalFlag, timeWindowFlag);
                });

                listenerThread = new Thread(() -> {
                    try {
                        // 模拟等待时间窗口5min内收到真实action
                        long randomDelay = (long) (Math.random() * 10000);
                        Thread.sleep(randomDelay);

                        // 修改第一个原子变量 taskStatus
                        boolean newStatus = Math.random() <= TRUE_ACTION_ARRIVAL_PERCENT;
                        arrivalFlag.set(newStatus);
//                        System.out.println(taskName + " 任务状态修改为: " + newStatus);

                        // 如果 arrivalFlag 为 true，立即停止线程并设置 timeWindowFlag
                        if (arrivalFlag.get()) {
                            timeWindowFlag.set(true);
                            return; // 退出当前线程
                        }

                        // 如果 arrivalFlag 为 false，模拟消耗完剩下的等待时间
                        long remainingTime = 10000 - randomDelay;
                        Thread.sleep(remainingTime); // 剩余时间

                        // 等待完毕后修改 stopFlag 为 true，结束线程
                        timeWindowFlag.set(true);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }

            void start() {
                taskThread.start();

                // 防止 listenerThread 线程先执行
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                listenerThread.start();
            }
        }

        TaskWithListener taskWithListener = new TaskWithListener(taskName, triggerDevices, actionDevices, execFunc);
        taskWithListener.start();
    }
}
