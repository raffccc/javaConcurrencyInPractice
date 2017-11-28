package chapter14.warn;

import chapter14.util.BaseBoundedBuffer;

/**
 * Thread can sleep too much when the precondition is already satisfied.
 * The caller has to deal with InterruptedException
 */
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    private static final long SLEEP_GRANULARITY = 1000L;

    public SleepyBoundedBuffer(int capacity) {
        super(capacity);
    }
    
    public void put(V v) throws InterruptedException {
        while(true) {
            synchronized (this) {
                if (!isFull()) {
                    doPut(v);
                    return;
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }

    public V take() throws InterruptedException {
        while(true) {
            synchronized (this) {
                if (!isEmpty()) {
                    return doTake();
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }
    
}
