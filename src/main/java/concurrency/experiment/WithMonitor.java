package concurrency.experiment;

import concurrency.scheduling.RuleTree;
import concurrency.scheduling.TaskNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Date: 2024/12/30
 * @Author: 魏浩东
 * @Description: 基于优先级队列的线程池执行，确保规则任务按优先级提交，并保证设备依赖生成的顺序性。
 *               线程池线程数设置为 1，确保任务按提交顺序执行，不影响任务内部的多线程逻辑。
 */
public class WithMonitor {

    /**
     * 任务包装类，支持基于优先级的执行顺序
     */
    public static class PriorityRunnable implements Runnable, Comparable<PriorityRunnable> {
        private final int priority;
        private final Runnable task;

        public PriorityRunnable(int priority, Runnable task) {
            this.priority = priority;
            this.task = task;
        }

        @Override
        public void run() {
            task.run();
        }

        @Override
        public int compareTo(PriorityRunnable other) {
            return Integer.compare(this.priority, other.priority); // 规则 ID 越小，优先级越高
        }
    }

    /**
     * 运行规则任务
     * @param devicesFilePath 设备数据文件路径
     * @param rulesFilePath 规则数据文件路径
     */
    public static void runTasks(String devicesFilePath, String rulesFilePath) {
        RuleTree ruleTree = new RuleTree();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                1, 1, 60L, TimeUnit.SECONDS, new PriorityBlockingQueue<>());

        Gson gson = new Gson();

        try (
                FileReader deviceReader = new FileReader(devicesFilePath);
                FileReader ruleReader = new FileReader(rulesFilePath)
        ) {
            // 解析 JSON 文件
            List<DeviceInfo> devices = gson.fromJson(deviceReader, new TypeToken<List<DeviceInfo>>() {}.getType());
            List<RuleInfo> rules = gson.fromJson(ruleReader, new TypeToken<List<RuleInfo>>() {}.getType());

            // 注册设备并构建设备映射表
            Map<String, UUID> deviceMap = new HashMap<>();
            for (DeviceInfo device : devices) {
                UUID deviceUUID = UUID.randomUUID();
                ruleTree.createDevice(device.getName(), deviceUUID);
                deviceMap.put(device.getName(), deviceUUID);
            }

            // 按优先级提交任务
            for (RuleInfo rule : rules) {
                executorService.execute(new PriorityRunnable(rule.getId(), () -> processRule(rule, deviceMap, ruleTree)));
            }
        } catch (IOException e) {
            System.err.println("读取设备或规则文件失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 关闭线程池
            executorService.shutdown();
        }
    }

    /**
     * 处理规则任务
     * @param rule 规则信息
     * @param deviceMap 设备映射表
     * @param ruleTree 规则树
     */
    private static void processRule(RuleInfo rule, Map<String, UUID> deviceMap, RuleTree ruleTree) {
        try {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            for (String trigger : rule.getTriggers()) {
                if (deviceMap.containsKey(trigger)) {
                    triggerDevices.add(deviceMap.get(trigger));
                } else {
                    System.err.println("触发设备未找到: " + trigger);
                }
            }

            for (String action : rule.getActions()) {
                if (deviceMap.containsKey(action)) {
                    actionDevices.add(deviceMap.get(action));
                } else {
                    System.err.println("动作设备未找到: " + action);
                }
            }

            // 创建任务并提交
            ruleTree.createTask("Rule-" + rule.getId(), triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("Rule-" + rule.getId(), rule.getDescription()));

            Thread.sleep(100);
        } catch (Exception e) {
            System.err.println("规则执行失败 (Rule-" + rule.getId() + "): " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String executionLogPath = "E:\\研究生信息收集\\论文材料\\IoT-Event-Proxy\\src\\main\\java\\concurrency\\experiment\\data\\WithMonitorLog.txt";
        String devicesPath = "E:\\研究生信息收集\\论文材料\\IoT-Event-Proxy\\src\\main\\java\\concurrency\\experiment\\data\\Device.json";
        String rulesPath = "E:\\研究生信息收集\\论文材料\\IoT-Event-Proxy\\src\\main\\java\\concurrency\\experiment\\data\\StaticRules.json";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(executionLogPath, false))) {
            // 清空执行日志
        } catch (IOException e) {
            System.err.println("无法清空执行日志: " + e.getMessage());
        }

        runTasks(devicesPath, rulesPath);
    }
}
