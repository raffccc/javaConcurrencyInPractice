package chapter5.correct;

/**
 * Created by rafcar on 7/3/17.
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
