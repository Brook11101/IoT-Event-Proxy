package concurrency.utils;

/**
 * @ClassName PriorityCallable
 * @Author 魏浩东
 * @Date 2025/2/17 上午10:50
 */


import java.util.concurrent.Callable;

/**
 * 任务包装类，支持优先级调度。
 * 这里使用泛型 T 使其可复用于不同的任务类型。
 */
public class PriorityCallable<T> implements Callable<T>, Comparable<PriorityCallable<T>> {
    private final int priority;
    private final Callable<T> task;

    public PriorityCallable(int priority, Callable<T> task) {
        this.priority = priority;
        this.task = task;
    }

    @Override
    public T call() throws Exception {
        return task.call();
    }

    @Override
    public int compareTo(PriorityCallable<T> other) {
        return Integer.compare(this.priority, other.priority); // 规则 ID 越小，优先级越高
    }

    public int getPriority() {
        return priority;
    }
}
