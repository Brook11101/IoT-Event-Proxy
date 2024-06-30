package concurrency;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskNode {
    private RootNode root;
    private UUID taskUUID;
    private String taskName;
    private CopyOnWriteArraySet<TaskNode> dependencies = new CopyOnWriteArraySet<>();
    private CopyOnWriteArraySet<TaskNode> notifies = new CopyOnWriteArraySet<>();
    private Set<UUID> triggerDevices;
    private Set<UUID> actionDevices;
    private Long timeStamp;
    private ExecFunc execFunction;
    private AtomicBoolean isFinishing;
    public TaskNode(RootNode root, UUID taskUUID, String taskName, Set<UUID> triggerDevices, Set<UUID> actionDevices, ExecFunc execFunction) {
        this.root = root;
        this.taskUUID = taskUUID;
        this.taskName = taskName;
        this.triggerDevices = triggerDevices;
        this.actionDevices = actionDevices;
        this.timeStamp = System.currentTimeMillis();
        this.execFunction = execFunction;
        this.isFinishing = new AtomicBoolean(false);
    }

    public interface ExecFunc {
        void exec() throws InterruptedException;
    }

    public UUID getTaskUUID() {
        return taskUUID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void runTask() {
        //逻辑节点插入
        init();
        while (!dependencies.isEmpty()) ;
        //执行等待窗口
        executeAction();
        //使用随机数模拟真实action是否到来
        Random random = new Random();
        boolean trulyCome = random.nextInt(100) < 90;
        if (trulyCome) {
            //实时重新生成依赖关系
            init();
            //等待新加入的依赖
            while (!dependencies.isEmpty()) ;
        }
        isFinishing.set(true);
        notifyOthers();
        removeSelf();
    }

    private void init() {
        Set<DeviceNode> devices = root.getDeviceNodes();

        // 将该任务执行动作在每个device下都添加任务

        //这里存在欠缺，因为并没有考虑到对添加的action device node上的其他任务正在执行，所以没有添加相关依赖

        //问题：少规则2啊
        devices.forEach((device) -> {
            if (actionDevices.contains(device.getDeviceUUID())) {

                ///////这里存在欠缺，因为并没有考虑到对添加的action device node上的其他任务正在执行，所以没有添加相关依赖
                device.getTaskNodes().forEach((t) -> {
                    if (t.timeStamp < this.timeStamp && !t.isFinishing.get()) {
                        t.notifies.add(this);
                        dependencies.add(t);
                    }
                });
                device.getTaskNodes().add(this);
            }
        });

        // 对于所有的trigger依赖，添加任务到每个task的notify中，并在自己的dependency中添加别人
        devices.forEach((device) -> {
            if (triggerDevices.contains(device.getDeviceUUID())) {
                device.getTaskNodes().forEach((t) -> {
                    if (t.timeStamp < this.timeStamp && !t.isFinishing.get()) {
                        t.notifies.add(this);
                        dependencies.add(t);
                    }
                });
            }
        });

        System.out.printf("TASK %s DEPENDS ON %s%n", this.taskName, this.dependencies.toString());
    }

    private void executeAction() {
        Set<DeviceNode> devices = root.getDeviceNodes();
        devices.forEach((device) -> {
            if (actionDevices.contains(device.getDeviceUUID())) {
                device.getLock().writeLock().lock();
            }
        });

        System.out.printf("TASK %s START EXECUTE%n", this.taskName);

        try {
            execFunction.exec();
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.printf("TASK %s EXECUTED%n", this.taskName);

        devices.forEach((device) -> {
            if (actionDevices.contains(device.getDeviceUUID())) {
                device.getLock().writeLock().unlock();
            }
        });
    }

    //主动去修改别的任务的dependencies
    private void notifyOthers() {
        notifies.forEach((n) -> n.dependencies.remove(this));
    }

    private void removeSelf() {
        Set<DeviceNode> devices = root.getDeviceNodes();

        devices.forEach((device) -> {
            if (actionDevices.contains(device.getDeviceUUID())) {
                device.getTaskNodes().remove(this);
            }
        });
    }
}
