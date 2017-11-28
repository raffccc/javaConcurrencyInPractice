package chapter8.correct;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorExample {

    private static final int N_THREADS = 3;
    private static final int CAPACITY = 10;
    
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(N_THREADS, N_THREADS, 
                        0L, TimeUnit.MILLISECONDS, 
                        new LinkedBlockingQueue<>(CAPACITY));
        
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        
        executor.shutdown();
    }
    
}
