package chapter5.correct;

import java.util.Vector;

/**
 * By acquiring the collection lock we can make getLast and deleteLast atomic, ensuring that the size of the Vector does not change 
 * between calling size and get.
 */
public class VectorCompoundActionsClientLock {

    public static Object getLast(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }
    }

    public static void deleteLast(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            list.remove(lastIndex);
        }
    }
}
