package concurrency;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskNode {
    //所属根节点
    private RootNode root;

    private UUID taskUUID;
    private String taskName;

    //依赖集合：需要等待其他TaskNode先执行完成
    private CopyOnWriteArraySet<TaskNode> dependencies = new CopyOnWriteArraySet<>();
    //被依赖集合：等待当前TaskNode的集合
    private CopyOnWriteArraySet<TaskNode> notifies = new CopyOnWriteArraySet<>();
    //Trigger 设备集合
    private TreeSet<UUID> triggerDevices;
    //Action 设备集合
    private TreeSet<UUID> actionDevices;
    //根据Trigger推理出规则触发的时间
    private Long timeStamp;
    //规则执行内容
    private ExecFunc execFunction;

    //当前taskThread是否结束执行
    private AtomicBoolean hasFinished;

    public TaskNode(RootNode root, UUID taskUUID, String taskName, TreeSet<UUID> triggerDevices, TreeSet<UUID> actionDevices, SimpleExecFunc execFunction) {
        this.root = root;
        this.taskUUID = taskUUID;
        this.taskName = taskName;
        this.triggerDevices = triggerDevices;
        this.actionDevices = actionDevices;
        this.timeStamp = System.currentTimeMillis();
        this.execFunction = execFunction;
        this.hasFinished = new AtomicBoolean(false);
    }

    public UUID getTaskUUID() {
        return taskUUID;
    }

    public String getTaskName() {
        return taskName;
    }

    public Long getTimeStamp() {return timeStamp;}

    public void runTask(AtomicBoolean arrivalFlag, AtomicBoolean timeWindowFlag) {

        // 首先将当前 task 的 trigger devices 和 action devices 映射添加进去
        root.addRuleDeviceRelation(triggerDevices,this);
        //用于生成依赖关系
        generatingDependency();
        //持续等待，监听依赖是否为空
        while (!dependencies.isEmpty());


        //获取设备写锁，保证action的原子性
        Set<DeviceNode> devices = root.getDeviceNodes();
        devices.forEach((device) -> {
            if (actionDevices.contains(device.getDeviceUUID())) {
                device.getWriteLock().lock();
            }
        });

        System.out.printf("获取执行设备的写锁成功: ", this.taskName);

        //进入执行流程，获取锁，然后开始等待窗口
        executeAction(arrivalFlag, timeWindowFlag);
        hasFinished.set(true);

        //执行完通知其他TaskNode，同时将自己摘除
        cleanupDependenciesAndSelf();
        //在执行完成后将trigger devices与该 taskNode移除
        root.removeRuleDeviceRelation(triggerDevices,this);

        devices.forEach((device) -> {
            if (actionDevices.contains(device.getDeviceUUID())) {
                device.getWriteLock().unlock();
            }
        });
        System.out.printf("释放执行设备的写锁成功: ", this.taskName);
    }

    private void generatingDependency() {

//        Set<DeviceNode> devices = root.getDeviceNodes();
//        devices.forEach((device) -> {
//            if (actionDevices.contains(device.getDeviceUUID())) {
//                device.getReadLock().lock();
//            }
//        });
//        System.out.println("获取了相关设备的读锁 " + this.taskName + this.dependencies.toString() + this.notifies.toString());

        //调度规则2：作用在同一个device上的rule task按照时间的先后顺序执行
        root.getDeviceNodes().stream().forEach((device) -> {
            if (actionDevices.contains(device.getDeviceUUID())) {
                //先讲自己的占位符加进去
                device.getTaskNodes().add(this);
                //根据已有的生成依赖关系
                device.getTaskNodes().forEach((t) -> {
                    if (t.timeStamp < this.timeStamp && !t.hasFinished.get()) {
                        t.notifies.add(this);
                        dependencies.add(t);
                    }
                });
            }
        });

        //调度规则1：由同一个device触发的rule task，按照trigger触发的先后顺序，编排trigger对应的action的动作
        Set<TaskNode> taskNodesForTriggerDevices = root.getRuleDeviceRelation().get(triggerDevices);
        if (taskNodesForTriggerDevices != null) {
            taskNodesForTriggerDevices.stream().forEach((taskNode) -> {
                if (taskNode.getTimeStamp() < this.timeStamp && !taskNode.hasFinished.get()) {
                    taskNode.notifies.add(this);
                    this.dependencies.add(taskNode);
                }
            });
        }
        System.out.println("根据调度规则生成依赖关系完毕 " + this.taskName + this.dependencies.toString() + this.notifies.toString());

//        devices.forEach((device) -> {
//            if (actionDevices.contains(device.getDeviceUUID())) {
//                device.getReadLock().unlock();
//            }
//        });
//        System.out.println("释放了相关设备的读锁 " + this.taskName + this.dependencies.toString() + this.notifies.toString());
    }

    private void executeAction(AtomicBoolean arrivalFlag, AtomicBoolean timeWindowFlag) {

        System.out.printf("任务开始等待真实action: ", this.taskName);

        //判断真实action已到达或者等待时间窗口结束
        while (!arrivalFlag.get() && !timeWindowFlag.get());

        //处理真实action到达，开始正常执行
        if(arrivalFlag.get()){
            try {
                System.out.printf("任务收到真实action，开始执行: ", this.taskName);
                execFunction.exec();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else{
            if(timeWindowFlag.get()){
                System.out.printf("任务超出等待时间窗口，结束执行: ", this.taskName);
                return;
            }
        }
    }

    private void cleanupDependenciesAndSelf() {
        // 主动去修改别的任务的 dependencies
        notifies.forEach((n) -> n.dependencies.remove(this));

        // 从设备节点中移除自身
        Set<DeviceNode> devices = root.getDeviceNodes();
        devices.forEach((device) -> {
            if (actionDevices.contains(device.getDeviceUUID())) {
                device.getTaskNodes().remove(this);
            }
        });
    }


    public interface ExecFunc {
        void exec() throws InterruptedException;
    }

    public static class SimpleExecFunc implements ExecFunc {
        private final String taskName;

        public SimpleExecFunc(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void exec() throws InterruptedException {
            System.out.println("正在执行任务: " + taskName);
            Thread.sleep(1000);  // 模拟任务执行
            System.out.println("结束执行任务: " + taskName);
        }
    }

}
