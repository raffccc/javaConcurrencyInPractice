package chapter8.correct;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
    
    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return new MyAppThread(runnable, poolName);
    }
    
    private static class MyAppThread extends Thread {
        
        public MyAppThread(Runnable target, String name) {
            super(target, name);
        }
        
    }
}
