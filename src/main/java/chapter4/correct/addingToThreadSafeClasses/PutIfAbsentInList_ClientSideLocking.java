package chapter4.correct.addingToThreadSafeClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Adding Functionality to Existing Thread-safe Classes with client-side locking.
 * 
 * This violates the encapsulation of the synchronization policy.
 */
public class PutIfAbsentInList_ClientSideLocking<E> {

    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public boolean putIfAbsent(E x) {
        synchronized(list) {
            boolean absent = !list.contains(x);
            if (absent) {
                list.add(x);
            }
            return absent;
        }
    }
    
}
