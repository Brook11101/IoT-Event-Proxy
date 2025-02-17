package concurrency.utils;
import java.util.concurrent.*;

/**
 * @ClassName PriorityThreadPoolExecutor
 * @Author 魏浩东
 * @Date 2025/2/17 上午10:55
 */


/**
 * 自定义线程池，重写 newTaskFor 方法以返回自定义的 PriorityFutureTask。
 */
public class PriorityThreadPoolExecutor extends ThreadPoolExecutor {
    public PriorityThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                      PriorityBlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        if (callable instanceof PriorityCallable) {
            return new PriorityFutureTask<>((PriorityCallable<T>) callable);
        }
        return super.newTaskFor(callable);
    }
}