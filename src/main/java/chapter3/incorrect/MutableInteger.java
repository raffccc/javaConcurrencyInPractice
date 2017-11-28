package chapter3.incorrect;

/**
 * Not Thread Safe.
 * 
 * Value is accessed from both get and set without synchronization. It is susceptible to stale value.
 * If one thread calls set, other threads calling get may or may not see that update
 */
public class MutableInteger {
    
    private int value;
    
    public int get() {
        return value;
    }
    
    public void set(int value) {
        this.value = value;
    }
    
}
