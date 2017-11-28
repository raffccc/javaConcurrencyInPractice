package chapter4.correct.addingToThreadSafeClasses;

import java.util.Vector;

/**
 * Approach to add functionality to existing thread-safe class
 * 
 * Not all classes expose enough of their state to sub-classes to admit this approach.
 * 
 * Extension is more fragile than adding code directly to a class, because the implementation of the synchronization policy
 * is not distributed over multiple, separately maintained source files. If the underlying class were to change its synchronization
 * policy by choosing a different lock to guard its state variables, the subclass would subtly and silently break, because it
 * no longer used the right lock to control concurrent access to the base class's state.
 */
public class BetterVector_Extension<E> extends Vector<E> {
    
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent) {
            add(x);
        }
        return absent;
    } 
    
}
