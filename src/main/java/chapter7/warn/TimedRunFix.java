package chapter7.warn;

import util.ExceptionUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Fixes the issues of {@link chapter7.incorrect.TimedRun}, but because it relies on a timed join, it shares a 
 * deficiency with join: we don't know if control was returned because the thread exited normally or because the join
 * timed out.
 * 
 * Interrupts a task in a dedicated Thread.
 */
public class TimedRunFix {

    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(3);

    public static void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;
            
            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }
            
            void rethrow() {
                if (t != null) {
                    throw ExceptionUtils.launderThrowable(t);
                }
            }
        }
        
        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();

        cancelExec.schedule(() -> taskThread.interrupt(), timeout, unit);
        
        taskThread.join(unit.toMillis(timeout));
        task.rethrow();
    }
    
}
