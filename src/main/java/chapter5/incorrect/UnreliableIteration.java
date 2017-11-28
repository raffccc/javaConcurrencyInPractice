package chapter5.incorrect;

import java.util.Vector;

/**
 * This example shows and iteration that may throw {@link ArrayIndexOutOfBoundsException}
 */
public class UnreliableIteration {
    
    public void iterationExample(Vector vector) {
        for (int i = 0; i < vector.size(); i++) {
            //When a thread reaches this code, the value may already have been deleted by another thread. 
            System.out.println(vector.get(i));
        }
    }
    
}
