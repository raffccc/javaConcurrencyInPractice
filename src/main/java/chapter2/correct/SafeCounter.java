package chapter2.correct;

import java.util.concurrent.atomic.AtomicLong;

public class SafeCounter {
    
    private AtomicLong count = new AtomicLong(0);

    public long getCount() {
        return count.get();
    }
    
    //This is not thread-safe, multiple threads can see the same value for count. Read-modify-write operation
    public void incrementCount() {
        count.incrementAndGet();
    }
    
}
