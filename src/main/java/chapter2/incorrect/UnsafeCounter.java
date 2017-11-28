package chapter2.incorrect;

public class UnsafeCounter {
    
    private long count = 0;

    public long getCount() {
        return count;
    }
    
    //This is not thread-safe, multiple threads can see the same value for count. Read-modify-write operation
    public void incrementCount() {
        ++count;
    }
    
}
