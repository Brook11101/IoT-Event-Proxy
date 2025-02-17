package concurrency.experiment;

import concurrency.scheduling.RuleTree;
import concurrency.scheduling.TaskNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import concurrency.utils.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;


/**
 * @Date: 2025/2/17
 * @Author: 魏浩东
 * @Description: 按轮次执行任务，确保上一轮所有任务执行完成后，下一轮才启动。
 */
public class WithMonitor {

    /**
     * 运行规则任务（按轮次）。
     *
     * @param devicesFilePath 设备数据文件路径
     * @param rulesFilePath   规则数据文件路径
     */
    public static void runTasks(String devicesFilePath, String rulesFilePath) {
        RuleTree ruleTree = new RuleTree();
        Gson gson = new Gson();

        try (
                FileReader deviceReader = new FileReader(devicesFilePath);
                FileReader ruleReader = new FileReader(rulesFilePath)
        ) {
            // 解析设备和规则
            List<DeviceInfo> devices = gson.fromJson(deviceReader, new TypeToken<List<DeviceInfo>>() {}.getType());
            List<List<RuleInfo>> ruleRounds = gson.fromJson(ruleReader, new TypeToken<List<List<RuleInfo>>>() {}.getType());

            // 注册设备并构建设备映射表
            Map<String, UUID> deviceMap = new HashMap<>();
            for (DeviceInfo device : devices) {
                UUID deviceUUID = UUID.randomUUID();
                ruleTree.createDevice(device.getName(), deviceUUID);
                deviceMap.put(device.getName(), deviceUUID);
            }

            // 按轮次执行任务
            for (int round = 0; round < ruleRounds.size(); round++) {
                List<RuleInfo> rules = ruleRounds.get(round);

                System.out.println("开始执行第 " + (round + 1) + " 轮任务，任务数量：" + rules.size());

                // 使用自定义的 PriorityThreadPoolExecutor
                PriorityThreadPoolExecutor executorService = new PriorityThreadPoolExecutor(
                        1, 1, 60L, TimeUnit.SECONDS, new PriorityBlockingQueue<>());

                List<Future<List<Thread>>> futures = new ArrayList<>();

                for (RuleInfo rule : rules) {
                    PriorityCallable callableTask = new PriorityCallable(rule.getId(), () -> processRule(rule, deviceMap, ruleTree));
                    Future<List<Thread>> future = executorService.submit(callableTask);
                    futures.add(future);
                }

                // 关闭线程池并等待所有任务执行完成
                executorService.shutdown();
                try {
                    executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // 收集所有内部线程
                List<Thread> allThreads = new ArrayList<>();
                for (Future<List<Thread>> future : futures) {
                    try {
                        List<Thread> threads = future.get();
                        if (threads != null) {
                            allThreads.addAll(threads);
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }

                // 等待所有内部线程结束
                for (Thread thread : allThreads) {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                System.out.println("第 " + (round + 1) + " 轮任务全部完成。");
            }

        } catch (IOException e) {
            System.err.println("读取文件或执行任务失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 处理规则任务，将创建的内部线程返回给调用者。
     *
     * @param rule      规则信息
     * @param deviceMap 设备映射表
     * @param ruleTree  规则树
     * @return 该规则任务内部新建的线程列表
     */
    private static List<Thread> processRule(RuleInfo rule, Map<String, UUID> deviceMap, RuleTree ruleTree) {
        List<Thread> threads = new ArrayList<>();
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

            // 创建任务并获取内部线程
            List<Thread> createdThreads = ruleTree.createTask("Rule-" + rule.getId(), triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("Rule-" + rule.getId(), rule.getDescription()));
            threads.addAll(createdThreads);

            // 模拟任务执行间隔
            Thread.sleep(100);
        } catch (Exception e) {
            System.err.println("规则执行失败 (Rule-" + rule.getId() + "): " + e.getMessage());
            e.printStackTrace();
        }
        return threads;
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
