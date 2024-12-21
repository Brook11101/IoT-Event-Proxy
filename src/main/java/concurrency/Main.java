package concurrency;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class Main {
public static void main(String[] args) throws InterruptedException {
RuleTree ruleTree = new RuleTree();

ExecutorService executorService = Executors.newFixedThreadPool(50);

// ============ 生成设备部分 ============
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

// ============ 生成规则部分 ============
{

    TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

            triggerDevices.add(plug);
            triggerDevices.add(wyze);
            triggerDevices.add(phone);

            actionDevices.add(bulb);
            actionDevices.add(camera);

        submitTask(executorService, ruleTree, "rule1",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("rule1"));
        sleep(500);
        }
{

    TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

            triggerDevices.add(bulb);

            actionDevices.add(plug);

        submitTask(executorService, ruleTree, "rule2",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("rule2"));
        sleep(500);
        }
{

    TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

            triggerDevices.add(bulb);

            actionDevices.add(phone);
            actionDevices.add(camera);

        submitTask(executorService, ruleTree, "rule3",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("rule3"));
        sleep(500);
        }
{

    TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

            triggerDevices.add(wyze);

            actionDevices.add(bulb);
            actionDevices.add(camera);

        submitTask(executorService, ruleTree, "rule4",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("rule4"));
        sleep(500);
        }
{

    TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

            triggerDevices.add(wyze);

            actionDevices.add(robot);
            actionDevices.add(ring);

        submitTask(executorService, ruleTree, "rule5",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("rule5"));
        sleep(500);
        }

        executorService.shutdown();
        }

        private static void submitTask(ExecutorService executorService,
        RuleTree ruleTree,
        String taskName,
        TreeSet<UUID> triggerDevices, TreeSet<UUID> actionDevices, TaskNode.SimpleExecFunc execFunc) { executorService.submit(() -> {
                Thread.currentThread().setName(taskName);
                ruleTree.createTask(taskName, triggerDevices, actionDevices, execFunc);
                });
                }
                }
