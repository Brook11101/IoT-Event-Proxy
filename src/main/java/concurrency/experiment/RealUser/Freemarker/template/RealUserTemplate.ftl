package concurrency.experiment.RealUser.Freemarker;

import concurrency.scheduling.RuleTree;
import concurrency.scheduling.TaskNode;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class RealUserRuleExec {
public static void main(String[] args) throws InterruptedException {
RuleTree ruleTree = new RuleTree();

ExecutorService executorService = Executors.newFixedThreadPool(1);

// ============ 生成设备部分 ============
<#list devices as dev>
    UUID ${dev.uuidVar} = UUID.randomUUID();
    ruleTree.createDevice("${dev.name}", ${dev.uuidVar});
</#list>

// ============ 生成规则部分 ============
<#list rules as rule>
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
        <#list rule.triggers as tDev>
            triggerDevices.add(${tDev});
        </#list>

        // 遍历 actions
        <#list rule.actions as aDev>
            actionDevices.add(${aDev});
        </#list>

        submitTask(executorService, ruleTree, "Rule-${rule.id}",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("${rule.description}"));
        sleep(500);
        }
        </#list>

        executorService.shutdown();
        }

        private static void submitTask(ExecutorService executorService,
        RuleTree ruleTree,
        String taskName,
        TreeSet<UUID> triggerDevices, TreeSet<UUID> actionDevices,
                TaskNode.SimpleExecFunc execFunc) {
                executorService.submit(() -> {
                Thread.currentThread().setName(taskName);
                ruleTree.createTask(taskName, triggerDevices, actionDevices, execFunc);
                });
                }
                }
