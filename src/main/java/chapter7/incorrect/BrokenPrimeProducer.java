package chapter7.incorrect;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class BrokenPrimeProducer extends Thread {
    
    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;
    
    public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }
    
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!cancelled) {
                /*
                 * If this queue gets full, the thread will get stuck because it will never be able to see the
                 * cancellation request.
                 */
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException consumed) {}
    }
    
    public void cancel() {
        this.cancelled = true;
    }
    
}
