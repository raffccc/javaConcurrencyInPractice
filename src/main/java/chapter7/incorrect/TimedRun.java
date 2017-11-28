package chapter7.incorrect;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Violates the rules: you should know a thread's interruption policy before interrupting it.
 * 
 * Since timedRun can be called from an arbitrary thread, it cannot know the calling thread's interruption policy.
 * 
 * If the task completes before the timeout, the cancellation task that interrupts the thread in which timedRun was
 * called could go off after timedRun has returned to its caller.
 * 
 * If the task is not responsive to interruption, timedRun will not return until the task finishes, which may be
 * long after the desired timeout (or not at all)
 */
public class TimedRun {
    
    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(3);
    
    public static void timedRun(Runnable r, long timeout, TimeUnit unit) {
        final Thread taskThread = Thread.currentThread();
        cancelExec.schedule(() -> taskThread.interrupt(), timeout, unit);
        r.run();
    }
    
}
