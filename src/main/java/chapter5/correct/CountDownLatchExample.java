package chapter5.correct;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * Creates a number of threads that run a given task concurrently. 
 * 
 * The first thing each worker thread does is wait on the starting gate; this ensures that none of them starts working until they 
 * all are ready to start.
 * 
 * The last thing each does is count down on the ending gate; this allows the master thread to wait efficiently until the last of 
 * the worker threads has finished, so it can calculate the elapsed time.
 */
public class CountDownLatchExample {
    
    public static long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread(() -> {
                try {
                    startGate.await();
                    
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException ignored) {
                }
            });
            t.start();
        }

        System.out.println("All threads are blocked waiting for startGate to reach 0");
        
        long start = System.nanoTime();
        startGate.countDown();

        System.out.println("The calculation of the end time is blocked until all threads have completed");
        endGate.await();
        long end = System.nanoTime();
        
        return end - start;
    }

    public static void main(String[] args) throws InterruptedException {
        long duration = CountDownLatchExample.timeTasks(300, () -> System.out.println("Running: " + UUID.randomUUID()));
        System.out.println("Duration in secs: " + duration/Math.pow(10, 9));
    }
    
}
