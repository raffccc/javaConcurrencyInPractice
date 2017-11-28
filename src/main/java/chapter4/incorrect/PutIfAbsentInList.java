package chapter4.incorrect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class provides only the illusion of synchronization; the various list oerations, while all synchronized, use different
 * locks, which means that putIfAbsent is not atomic relative to other operations on the List. So there is no guarantee that
 * another thread won' modify the list while putIfAbsent is executing
 */
public class PutIfAbsentInList<E> {
    
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());
    
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent) {
            list.add(x);
        }
        return absent;
    }
    
}
