package znode.proxy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class proxyCreate {

    private final String EVENT_INPUT_FILE_PATH;
    private final ExecutorService executorService;

    public proxyCreate(String inputEventFilePath) {
        this.EVENT_INPUT_FILE_PATH = inputEventFilePath;
        this.executorService = Executors.newFixedThreadPool(10);
    }

//    public CompletableFuture<List<String>> dynamicProxy() {
//        CompletableFuture<List<String>> allResults = new CompletableFuture<>();
//
//        try (FileReader fileReader = new FileReader(EVENT_INPUT_FILE_PATH);
//             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
//
//            List<String> lines = bufferedReader.lines().collect(Collectors.toList());
//            List<CompletableFuture<String>> allFutures = lines.stream()
//                    .map(line -> {
//                        line = line.replaceAll("[\\r\\n\\s]", "");
//                        System.out.println("New line detected: " + line);
//                        String[] targets = line.split(",");
//                        return createCompletableFutures(targets);
//                    })
//                    .flatMap(List::stream)
//                    .collect(Collectors.toList());
//
//            CompletableFuture<Object> anyOfFutures = CompletableFuture.anyOf(allFutures.toArray(new CompletableFuture[0]));
//
    //注意，这里使用的是thenApplySync的方法去异步的收集异步线程的返回结果,用于在异步操作完成后应用一个函数来处理结果,即这里的收集结果并写入map中，但这样写如map存在缺陷，无法全部收集
//            allResults = anyOfFutures.thenApplyAsync(result -> {
//                List<String> completedResults = allFutures.stream()
//                        .filter(cf -> cf.isDone() && !cf.isCompletedExceptionally())
//                        .map(cf -> {
//                            try {
//                                return cf.get();
//                            } catch (InterruptedException | ExecutionException e) {
//                                throw new RuntimeException(e);
//                            }
//                        })
//                        .collect(Collectors.toList());
//                return completedResults;
//            });
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return allResults;
//    }

    public CompletableFuture<List<String>> dynamicProxy() {
        CompletableFuture<List<String>> allResults = new CompletableFuture<>();

        try (FileReader fileReader = new FileReader(EVENT_INPUT_FILE_PATH);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            List<String> lines = bufferedReader.lines().collect(Collectors.toList());
            List<CompletableFuture<String>> allFutures = lines.stream()
                    .map(line -> {
                        line = line.replaceAll("[\\r\\n\\s]", "");
                        System.out.println("New line detected: " + line);
                        String[] targets = line.split(",");
                        return createCompletableFutures(targets);
                    })
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

            CompletableFuture<Object> anyOfFutures = CompletableFuture.anyOf(allFutures.toArray(new CompletableFuture[0]));

            List<String> results = new ArrayList<>(); // 创建一个用于收集结果的列表

            // 输出每个 CompletableFuture 的结果并添加到 results 中
            // 这里是根据allFuture的数组去判断线程是否结束运行
            for (int i = 0; i < allFutures.size(); i++) {
                int index = i;
                allFutures.get(i).whenCompleteAsync((result, throwable) -> {
                    if (throwable == null) {
                        //不使用异步的thenApplyAsync去收集结果，而是通过在whenCompleteAsync中去主动的添加结果
                        results.add(result); // 将结果添加到 results 中
                    } else {
                        throwable.printStackTrace();
                    }
                    // 判断是否是最后一个 CompletableFuture 完成，如果是，则将结果传递给 allResults
                    if (index == allFutures.size() - 1) {
                        allResults.complete(results);
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return allResults;
    }


    private List<CompletableFuture<String>> createCompletableFutures(String[] targets) {
        return Arrays.stream(targets)
                .map(target -> CompletableFuture.supplyAsync(() -> threadBuilder(target), executorService)
                        .whenCompleteAsync((result, throwable) -> {
                            if (throwable == null) {
                                System.out.println("Async Thread Complete: " + target);
                            } else {
                                throwable.printStackTrace();
                            }
                        }))
                .collect(Collectors.toList());
    }

    public String threadBuilder(String threadName) {
        System.out.println("Thread " + threadName + " is running");
        if (threadName.equals("rule10"))
            while (true) ;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Thread " + threadName + " finished";
    }

    public void shutdownThreadPool() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        proxyCreate pc = new proxyCreate("./src/main/java/znode.file/IoTEvent.txt");
        CompletableFuture<List<String>> allResults = pc.dynamicProxy();

        try {
            List<String> results = allResults.join();
            for (String result : results) {
                System.out.println(result);
            }
        } catch (CompletionException e) {
            e.printStackTrace();
        }


        pc.shutdownThreadPool();
    }
}

