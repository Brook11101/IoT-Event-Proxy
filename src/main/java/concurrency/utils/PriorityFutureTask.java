package concurrency.utils;
import java.util.concurrent.FutureTask;

/**
 * @ClassName PriorityFutureTask
 * @Author 魏浩东
 * @Date 2025/2/17 上午10:51
 */


/**
 * 自定义 FutureTask，实现 Comparable 接口，用于在 PriorityBlockingQueue 中排序。
 */
public class PriorityFutureTask<T> extends FutureTask<T> implements Comparable<PriorityFutureTask<T>> {
    private final int priority;

    public PriorityFutureTask(PriorityCallable<T> callable) {
        super(callable);
        this.priority = callable.getPriority();
    }

    @Override
    public int compareTo(PriorityFutureTask<T> o) {
        return Integer.compare(this.priority, o.priority);
    }
}