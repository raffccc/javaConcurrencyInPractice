package chapter7.correct;

import util.ExceptionUtils;

import java.util.concurrent.*;

public class TimedRunCancellingWithFuture {
    
    private static final ExecutorService taskExec = Executors.newFixedThreadPool(3);
    
    public static void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (ExecutionException e) {
            // exception thrown in task; rethrow
            throw ExceptionUtils.launderThrowable(e.getCause());
        } catch (TimeoutException e) {
            // Task will be cancelled below
        } finally {
            // Harmless if task already completed
            task.cancel(true); // interrupt if running
        }

    }
    
}
