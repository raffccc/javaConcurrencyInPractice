package chapter13.correct;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockReleaseCanonicalForm {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            // update object state
            // catch exceptions and restore invariants if necessary
        } finally {
            lock.unlock();
        }
    }
    
}
