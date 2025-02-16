package concurrency.experiment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Date: 2025/1/5
 * @Author: 魏浩东
 * @Description: 模拟不使用调度的多线程并发执行，使用优先级队列确保任务按规则 ID 顺序调度
 */
public class ExecutorWithOutMonitor {

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
     * 运行并发任务
     * @param rulesFilePath 规则数据文件路径
     * @param logFilePath 日志文件路径
     */
    public static void runConcurrentTasks(String rulesFilePath, String logFilePath) {
        Gson gson = new Gson();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                1, 1, 60L, TimeUnit.SECONDS, new PriorityBlockingQueue<>());

        try (
                FileReader ruleReader = new FileReader(rulesFilePath);
                BufferedWriter logWriter = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(logFilePath, false), StandardCharsets.UTF_8))
        ) {
            // 清空日志文件内容
            logWriter.write("");

            // 解析规则数据
            List<RuleInfo> rules = gson.fromJson(ruleReader, new TypeToken<List<RuleInfo>>() {}.getType());

            // 提交任务到线程池
            for (RuleInfo rule : rules) {
                executorService.execute(new PriorityRunnable(rule.getId(), () -> processRule(rule, logFilePath)));
            }
        } catch (IOException e) {
            System.err.println("读取规则文件或日志初始化失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 关闭线程池
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                    System.err.println("任务执行超时，强制关闭线程池...");
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                System.err.println("线程池终止异常: " + e.getMessage());
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 处理单个规则任务
     * @param rule 规则信息
     * @param logFilePath 日志文件路径
     */
    private static void processRule(RuleInfo rule, String logFilePath) {
        new Thread(() -> {
            try {
                System.out.println("启动: Rule-" + rule.getId());

                // 模拟任务随机延迟 1000-2000ms，模拟乱序流量
                int sleepTime = ThreadLocalRandom.current().nextInt(1000, 2000);
                Thread.sleep(sleepTime);

                // 构造日志内容
                String logEntry = String.format("%d,%s%n", rule.getId(), rule.getDescription());

                // 写入日志文件
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(logFilePath, true), StandardCharsets.UTF_8))) {
                    writer.write(logEntry);
                }

                System.out.printf("规则已执行: Rule-%d, 描述: %s, 睡眠时间: %d ms%n",
                        rule.getId(), rule.getDescription(), sleepTime);
            } catch (InterruptedException e) {
                System.err.println("线程被中断: Rule-" + rule.getId());
                Thread.currentThread().interrupt();
            } catch (IOException e) {
                System.err.println("日志写入失败: " + e.getMessage());
            }
        }).start(); // 启动独立线程

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        String rulesFilePath = "E:\\研究生信息收集\\论文材料\\IoT-Event-Proxy\\src\\main\\java\\concurrency\\experiment\\data\\StaticRules.json";
        String logFilePath = "E:\\研究生信息收集\\论文材料\\IoT-Event-Proxy\\src\\main\\java\\concurrency\\experiment\\data\\WithOutMonitorLog.txt";

        System.out.println("开始直接并发执行规则...");
        runConcurrentTasks(rulesFilePath, logFilePath);
    }
}
