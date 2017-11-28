package chapter6.warn;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Timer behaves poorly if a TimerTask throws an unchecked exception. An unchecked exception thrown from a TimerTask 
 * terminates the timer thread.
 * 
 * In this case, TimerTasks that are already scheduled but not yet executed are never run, and new tasks cannot be
 * scheduled.
 */
public class OutOfTime {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);
        TimeUnit.SECONDS.sleep(1);
        timer.schedule(new ThrowTask(), 1);
        TimeUnit.SECONDS.sleep(5);
    }

    private static class ThrowTask extends TimerTask {
        @Override
        public void run() {
            throw new RuntimeException();
        }
    }
}