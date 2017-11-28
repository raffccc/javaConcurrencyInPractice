package chapter7.correct;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class PrimeProducer extends Thread {
    
    private final BlockingQueue<BigInteger> queue;
    
    public PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }
    
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted()) {
                /*
                 * If this queue gets full, the thread will get stuck because it will never be able to see the
                 * cancellation request.
                 */
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException consumed) {}
    }
    
    public void cancel() {
        interrupt();
    }
    
}
