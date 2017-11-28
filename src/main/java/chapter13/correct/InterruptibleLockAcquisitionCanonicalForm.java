package chapter13.correct;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InterruptibleLockAcquisitionCanonicalForm {
    
    private Lock lock = new ReentrantLock();
    
    public boolean sendOnSharedLine(String message) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            return cancellableSendOnSharedLine(message);
        } finally {
            lock.unlock();
        }
    }

    private boolean cancellableSendOnSharedLine(String message) {
        return true;
    }

}
