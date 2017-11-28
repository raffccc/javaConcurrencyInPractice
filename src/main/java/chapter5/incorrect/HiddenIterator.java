package chapter5.incorrect;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * One have to remember to use locking everywhere a shared collection might be iterated.
 * 
 * This class is not Thread safe. The {@link HiddenIterator} lock should be acquired before using "set" in the println call. 
 */
public class HiddenIterator {
    
    private final Set<Integer> set = new HashSet<>();
    
    public synchronized void add(Integer i) {
        set.add(i);
    }
    
    public synchronized void remove(Integer i) {
        set.remove(i);
    }
    
    public void addTenThings() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            add(r.nextInt());
        }
        
        //This can throw ConcurrentModificationException
        System.out.println("DEBUG: added ten elements to " + set);
    }
    
}
