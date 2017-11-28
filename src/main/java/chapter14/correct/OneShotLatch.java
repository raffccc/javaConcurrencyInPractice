package chapter14.correct;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class OneShotLatch {
    
    private final Sync sync = new Sync();
    
    public void signal() {
        sync.releaseShared(0);
    }
    
    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }
    
    private class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected int tryAcquireShared(int arg) {
            // Succeed if latch is open (state == 1), else fail
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1); // Latch is now open
            return true; // Other threads may now be able to acquire
        }
    }
    
}
