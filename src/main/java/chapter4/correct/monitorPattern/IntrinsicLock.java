package chapter4.correct.monitorPattern;

/**
 * Thread-safe Counter using the Java Monitor Pattern
 * 
 * This class encapsulates its state variable, {@code value}, and all access to that state variable is through its methods, which
 * are all {@code synchronized}.
 * 
 * Any lock object could be used to guard the object's state so long as it is used consistently. 
 * 
 * This class uses the class' intrinsic lock.
 */
public final class IntrinsicLock { 
    
    private long value = 0;
    
    public synchronized long getValue() {
        return value;
    }
    
    public synchronized void setValue(long value) {
        if (value == Long.MAX_VALUE) {
            throw new IllegalStateException("counter overflow");
        }
        this.value = value;
    }
    
}
