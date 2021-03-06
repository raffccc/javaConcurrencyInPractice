package chapter5.correct;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Scalable cache implementation using {@link Future}.
 */
public class Memoizer<A, V> implements Computable<A, V> {
    
    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;
    
    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }
    
    @Override
    public V compute(final A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            
            if (f == null) {
                Callable<V> eval = () -> c.compute(arg);
                FutureTask<V> ft = new FutureTask<>(eval);
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }

            try {
                return f.get();
            } catch (CancellationException e1) {
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
