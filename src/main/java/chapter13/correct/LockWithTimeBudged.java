package chapter13.correct;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockWithTimeBudged {

    private Lock lock = new ReentrantLock();
    
    public boolean trySendSharedLine(String message, long timeout, TimeUnit unit) throws InterruptedException {
        long nanosToLock = unit.toNanos(timeout) - estimatedNanosToSend(message);
        
        if (!lock.tryLock(nanosToLock, TimeUnit.NANOSECONDS)) {
            return false;
        }
        
        try {
            return sendOnSharedLine(message);
        } finally {
            lock.unlock();
        }
    }

    private boolean sendOnSharedLine(String message) {
        System.out.println(message);
        return true;
    }

    private long estimatedNanosToSend(String message) {
        return message.length();
    }

}
