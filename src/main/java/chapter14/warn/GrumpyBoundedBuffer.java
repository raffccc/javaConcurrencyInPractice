package chapter14.warn;

import chapter14.util.BaseBoundedBuffer;
import chapter14.util.BufferEmptyException;
import chapter14.util.BufferFullException;

/**
 * Annoying to use, exceptions are supposed to be for exceptional conditions
 */
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    public GrumpyBoundedBuffer(int capacity) {
        super(capacity);
    }
    
    public synchronized void put(V v) throws BufferFullException {
        if (isFull()) {
            throw new BufferFullException();
        }
        doPut(v);
    }
    
    public synchronized V take() throws BufferEmptyException {
        if (isEmpty()) {
            throw new BufferEmptyException();
        }
        return doTake();
    }

    // Example of usage
    public static void main(String[] args) throws InterruptedException {
        GrumpyBoundedBuffer<Integer> buffer = new GrumpyBoundedBuffer<>(3);
        while (true) {
            try {
                Integer item = buffer.take();
                // use item
                break;
            } catch (BufferEmptyException e) {
                long SLEEP_GRANULARITY = 1000L;
                Thread.sleep(SLEEP_GRANULARITY);
            }
        }
    }
    
}
