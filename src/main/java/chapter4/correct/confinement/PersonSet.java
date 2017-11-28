package chapter4.correct.confinement;

import java.util.HashSet;
import java.util.Set;

import chapter4.shared.Person;

/**
 * Using Confinement to Ensure Thread Safety
 * 
 * The state of PersonSet is managed by a HashSet, which is not thread-safe.
 * But because mySet is private and not allowed to escape, the HashSet is confined to the PersonSet.
 * The only code paths that can access mySet are addPerson and containsPerson, and each of these acquires the lock on the PersonSet.
 * All its state is guarded by its intrinsic lock, making PersonSet thread-safe.
 * 
 * This example makes no assumptions about the thread safety of Person, but if it is mutable, additional synchronization will be 
 * needed when accessing a Person retrieved from a PersonSet.
 */
public class PersonSet {
    
    private final Set<Person> mySet = new HashSet<Person>();
    
    public synchronized void addPerson(Person p) {
        mySet.add(p);
    }
    
    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }
}
