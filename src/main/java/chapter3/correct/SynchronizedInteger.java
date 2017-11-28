package chapter3.correct;

/**
 * Synchronizing only the setter would not be sufficient: threads calling get would still be able to see tale values.
 */
public class SynchronizedInteger {
    
    private int value;
    
    public synchronized int get() {
        return value;
    }
    
    public synchronized void set(int value) {
        this.value = value;
    }
    
}
