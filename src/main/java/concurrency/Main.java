package concurrency;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;


//TODO: LL/SC机制的实现

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RuleTree ruleTree = new RuleTree();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //所有设备
        UUID plug = UUID.randomUUID();
        ruleTree.createDevice("Wemo Smart Plug", plug);
        UUID bulb = UUID.randomUUID();
        ruleTree.createDevice("Yeelight Bulb", bulb);
        UUID camera = UUID.randomUUID();
        ruleTree.createDevice("Mijia Camera", camera);
        UUID phone = UUID.randomUUID();
        ruleTree.createDevice("Phone", phone);
        UUID wyze = UUID.randomUUID();
        ruleTree.createDevice("wyze", wyze);
        UUID robot = UUID.randomUUID();
        ruleTree.createDevice("robot", robot);
        UUID ring = UUID.randomUUID();
        ruleTree.createDevice("ring", ring);

        //所有规则
        TreeSet<UUID> rule1Triggers = new TreeSet<>();
        TreeSet<UUID> rule1Actions = new TreeSet<>();
        rule1Triggers.add(plug);
        rule1Actions.add(bulb);
        rule1Actions.add(camera);
        //ruleTree.createTask("rule1", rule1Triggers, rule1Actions, new TaskNode.SimpleExecFunc("task1"));
        submitTask(executorService, ruleTree, "task1", rule1Triggers, rule1Actions, new TaskNode.SimpleExecFunc("task1"));
        sleep(500);

        TreeSet<UUID> rule2Triggers = new TreeSet<>();
        TreeSet<UUID> rule2Actions = new TreeSet<>();
        rule2Triggers.add(bulb);
        rule2Actions.add(plug);
        //ruleTree.createTask("rule2", rule2Triggers, rule2Actions, new TaskNode.SimpleExecFunc("task2"));
        submitTask(executorService, ruleTree, "task2", rule2Triggers, rule2Actions, new TaskNode.SimpleExecFunc("task2"));
        sleep(500);


        TreeSet<UUID> rule3Triggers = new TreeSet<>();
        TreeSet<UUID> rule3Actions = new TreeSet<>();
        rule3Triggers.add(bulb);
        rule3Actions.add(phone);
        rule3Actions.add(camera);
        //ruleTree.createTask("rule3", rule3Triggers, rule3Actions, new TaskNode.SimpleExecFunc("task3"));
        submitTask(executorService, ruleTree, "task3", rule3Triggers, rule3Actions, new TaskNode.SimpleExecFunc("task3"));
        sleep(500);

        TreeSet<UUID> rule4Triggers = new TreeSet<>();
        TreeSet<UUID> rule4Actions = new TreeSet<>();
        rule4Triggers.add(wyze);
        rule4Actions.add(bulb);
        rule4Actions.add(camera);
        //ruleTree.createTask("rule4", rule4Triggers, rule4Actions, new TaskNode.SimpleExecFunc("task4"));
        submitTask(executorService, ruleTree, "task4", rule4Triggers, rule4Actions, new TaskNode.SimpleExecFunc("task4"));
        sleep(500);


        TreeSet<UUID> rule5Triggers = new TreeSet<>();
        TreeSet<UUID> rule5Actions = new TreeSet<>();
        rule5Triggers.add(wyze);
        rule5Actions.add(robot);
        rule5Actions.add(ring);
        //ruleTree.createTask("rule5", rule5Triggers, rule5Actions, new TaskNode.SimpleExecFunc("task5"));
        submitTask(executorService, ruleTree, "task5", rule5Triggers, rule5Actions, new TaskNode.SimpleExecFunc("task5"));
        executorService.shutdown();
    }

    private static void submitTask(ExecutorService executorService, RuleTree ruleTree, String taskName, TreeSet<UUID> triggerDevices, TreeSet<UUID> actionDevices, TaskNode.SimpleExecFunc execFunc) {
        // 提交任务到线程池
        executorService.submit(() -> {
            Thread.currentThread().setName(taskName);
            ruleTree.createTask(taskName, triggerDevices, actionDevices, new TaskNode.SimpleExecFunc(taskName));
        });
    }
}