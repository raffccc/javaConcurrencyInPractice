package chapter5.incorrect;

import java.util.Vector;

/**
 * The methods seem harmless, they can' corrupt the {@link Vector}, no matter how many threads call them simultaneously
 * 
 * If Thread A calls getLast on a Vector with ten elements, thread B calls deleteLast on the same Vector and the operations 
 * are interleaved, getLast throws {@link ArrayIndexOutOfBoundsException}
 */
public class VectorCompoundActionsConfusingResults {
    
    public static Object getLast(Vector list) {
        int lastIndex = list.size() - 1;
        return list.get(lastIndex);
    }
    
    public static void deleteLast(Vector list) {
        int lastIndex = list.size() - 1;
        list.remove(lastIndex);
    }
    
}
