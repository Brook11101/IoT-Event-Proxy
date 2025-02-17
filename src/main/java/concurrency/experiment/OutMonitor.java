package concurrency.experiment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import concurrency.utils.PriorityCallable;
import concurrency.utils.PriorityThreadPoolExecutor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Date: 2025/1/5
 * @Author: 魏浩东
 * @Description: 模拟不使用调度的多线程并发执行，使用优先级队列确保任务按规则 ID 顺序调度
 */
public class OutMonitor {

    /**
     * 运行并发任务
     *
     * @param rulesFilePath 规则数据文件路径
     * @param logFilePath   日志文件路径
     */
    public static void runConcurrentTasks(String rulesFilePath, String logFilePath) {
        Gson gson = new Gson();

        try (
                FileReader ruleReader = new FileReader(rulesFilePath);
                BufferedWriter logWriter = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(logFilePath, false), StandardCharsets.UTF_8))
        ) {
            // 清空日志文件内容
            logWriter.write("");

            // 解析 JSON 文件，获取按轮次划分的规则
            List<List<RuleInfo>> ruleRounds = gson.fromJson(ruleReader, new TypeToken<List<List<RuleInfo>>>() {
            }.getType());

            // 按轮次执行任务
            for (int round = 0; round < ruleRounds.size(); round++) {
                List<RuleInfo> rules = ruleRounds.get(round);

                System.out.println("开始执行第 " + (round + 1) + " 轮任务，任务数量：" + rules.size());

                // 使用自定义的 PriorityThreadPoolExecutor
                PriorityThreadPoolExecutor executorService = new PriorityThreadPoolExecutor(
                        1, 1, 60L, TimeUnit.SECONDS, new PriorityBlockingQueue<>());

                List<Future<Thread>> futures = new ArrayList<>();

                for (RuleInfo rule : rules) {
                    PriorityCallable callableTask = new PriorityCallable(rule.getId(), () -> processRule(rule, logFilePath));
                    Future<Thread> future = executorService.submit(callableTask);
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
                for (Future<Thread> future : futures) {
                    try {
                        Thread threads = future.get();
                        if (threads != null) {
                            allThreads.add(threads);
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
     * 处理单个规则任务
     *
     * @param rule        规则信息
     * @param logFilePath 日志文件路径
     * @return 该规则任务内部新建的线程
     */
    private static Thread processRule(RuleInfo rule, String logFilePath) {
        Thread taskThread = new Thread(() -> {
            try {
                System.out.println("启动: Rule-" + rule.getId());
                long startTimeStamp = System.currentTimeMillis();

                // 模拟任务随机延迟 1000-2000ms，模拟乱序流量
                int sleepTime = ThreadLocalRandom.current().nextInt(1000, 2000);
                Thread.sleep(sleepTime);

                String time = String.valueOf(System.currentTimeMillis() - startTimeStamp);
                // 构造日志内容
                String logEntry = String.format("%d,%s%n", rule.getId(), time);

                // 写入日志文件
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(logFilePath, true), StandardCharsets.UTF_8))) {
                    writer.write(logEntry);
                }

                System.out.printf("规则已执行: Rule-%d, 时间: %s",
                        rule.getId(), time);
            } catch (InterruptedException e) {
                System.err.println("线程被中断: Rule-" + rule.getId());
                Thread.currentThread().interrupt();
            } catch (IOException e) {
                System.err.println("日志写入失败: " + e.getMessage());
            }
        }); // 启动独立线程

        taskThread.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return taskThread;
    }

    public static void main(String[] args) {
        String rulesFilePath = "E:\\研究生信息收集\\论文材料\\IoT-Event-Proxy\\src\\main\\java\\concurrency\\experiment\\data\\StaticRules.json";
        String logFilePath = "E:\\研究生信息收集\\论文材料\\IoT-Event-Proxy\\src\\main\\java\\concurrency\\experiment\\data\\OutMonitorLog.txt";

        System.out.println("开始直接并发执行规则...");
        runConcurrentTasks(rulesFilePath, logFilePath);
    }
}
