package chapter14.correct;

import chapter14.util.BaseBoundedBuffer;

public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {

    public BoundedBuffer(int capacity) {
        super(capacity);
    }
 
    public synchronized void put(V v) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        doPut(v);
        notifyAll();
    }
    
    public synchronized V take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        V v = doTake();
        notifyAll();
        return v;
    }
    
}