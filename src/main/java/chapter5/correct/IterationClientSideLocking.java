package chapter5.correct;

import java.util.Vector;

/**
 * This is an example that shows a safe iteration in a vector.
 * 
 * It has a high scalability cost, if the vector is big or the operations made on the elements in each iteration are expensive, 
 * all other operations in the vector will be blocked until this iteration finishes.
 * 
 * An alternative to locking the collection during iteration is to clone the collection and iterate the copy instead.
 * Since the clone is thread-confined, no other thread can modify it during iteration (The collection still must be locked during
 * the clone operation itself). The is an intrinsic performance cost in this approach.
 */
public class IterationClientSideLocking {

    public void iterationExample(Vector vector) {
        synchronized (vector) {
            for (int i = 0; i < vector.size(); i++) {
                System.out.println(vector.get(i));
            }
        }
    }
    
}
