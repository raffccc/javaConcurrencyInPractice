package chapter4.correct.monitorPattern;

import chapter4.shared.Person;

/**
 * This example of the monitor pattern uses a private lock to make the object thread-safe.
 * 
 * Advantages of using private lock over intrinsic lock (or any other public lock):
 *  - Making the lock object private encapsulates the lock so that client code cannot acquire it
 *  - Clients that improperly acquire another object' lock could cause liveness problems, and verifying that a publicly accessible 
 *  lock is properly used requires examining the entire program rather than a single class.
 * 
 * @see IntrinsicLock To another example of this pattern using the class's intrinsic lock
 */
public class PrivateLock {
    
    private final Object myLock = new Object();
    Person person;
    
    void someMethod() {
        synchronized (myLock) {
            //Access or modify the state of person
        }
    }
}
