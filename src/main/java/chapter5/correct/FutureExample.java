package chapter5.correct;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Example of the use of {@link java.util.concurrent.FutureTask} to make expensive calculations upfront while the current thread
 * does continues with other processing.
 */
public class FutureExample {
    
    private final FutureTask<Integer> future = new FutureTask<>(() -> { 
        Thread.sleep(3000); 
        return 1; 
    });
    
    private final Thread thread = new Thread(future);
    
    public void start() {
        thread.start();
    }
    
    public Integer get() throws ExecutionException, InterruptedException {
        return future.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureExample example = new FutureExample();
        example.start();
        
        System.out.println("Doing some other stuff while the future task is working");
        Thread.sleep(2500);

        long start = System.nanoTime();
        System.out.println("Now I will block a little waiting for the result of the future task");
        System.out.println(example.get());
        long end = System.nanoTime();

        System.out.println("Blocked for: " + (end - start)/Math.pow(10,9) + "s instead of 3s");
    }
    
}
