package concurrency.experiment.RealUser.ThreadPool;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import concurrency.experiment.RuleInfo;

import java.io.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Date：2025/1/5
 * @Time：下午4:26
 * @Author：魏浩东
 * @Description：模拟不使用调度的并发执行,使用优先级队列确保任务按规则ID顺序调度
 */
public class RealUserConcurrentExecutor {

    // 包装任务的类，带优先级
    public static class PriorityRunnable implements Runnable, Comparable<PriorityRunnable> {
        private final int priority; // 优先级
        private final Runnable task; // 实际任务

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
            return Integer.compare(this.priority, other.priority); // 优先级小的任务先执行
        }
    }

    public static void runConcurrentTasks(String rulesFilePath, String logFilePath) throws IOException {
        // 读取规则
        Gson gson = new Gson();
        List<RuleInfo> rules = gson.fromJson(new FileReader(rulesFilePath), new TypeToken<List<RuleInfo>>() {
        }.getType());

        // 创建线程池，核心线程数和最大线程数为规则数量
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                1, // 核心线程数
                1, // 最大线程数
                60L, // 空闲线程存活时间
                TimeUnit.SECONDS,
                new PriorityBlockingQueue<>() // 使用优先级队列
        );

        // 清空日志文件
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(logFilePath, false), "GBK"))) {
            writer.write(""); // 清空内容
        }

        // 提交任务到线程池
        for (RuleInfo rule : rules) {
            executorService.execute(new PriorityRunnable(rule.getId(), () -> {
                // 在任务中启动独立线程
                new Thread(() -> {
                    try {

                        System.out.println("启动: " + rule.getId());
                        // 模拟随机睡眠 0-3 秒，模拟乱序流量到达
                        int sleepTime = ThreadLocalRandom.current().nextInt(0, 3000);
                        Thread.sleep(sleepTime);

                        // 打印执行日志
                        String logEntry = String.format("%d,%s%n", rule.getId(), rule.getDescription());


                        // 写入日志
                        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                                new FileOutputStream(logFilePath, true), "GBK"))) {
                            writer.write(logEntry);
                        }


                        System.out.printf("规则已执行: Rule-%d, 描述: %s, 睡眠时间: %d ms%n",
                                rule.getId(), rule.getDescription(), sleepTime);

                    } catch (InterruptedException e) {
                        System.err.println("线程被中断: Rule-" + rule.getId());
                    } catch (IOException e) {
                        System.err.println("日志写入失败: " + e.getMessage());
                    }
                }).start(); // 启动独立线程

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }));
        }

        // 关闭线程池
        executorService.shutdown();
    }

    public static void main(String[] args) {
        try {
            String rulesFilePath = "E:\\研究生信息收集\\论文材料\\IoT-Event-Proxy\\src\\main\\java\\concurrency\\experiment\\RealUser\\ThreadPool\\json\\rules.json";
            String logFilePath = "E:\\研究生信息收集\\论文材料\\IoT-Event-Proxy\\src\\main\\java\\concurrency\\experiment\\RealUser\\ThreadPool\\json\\concurr_execution_log.txt";

            System.out.println("开始直接并发执行规则...");
            runConcurrentTasks(rulesFilePath, logFilePath);
        } catch (IOException e) {
            System.err.println("运行任务失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
