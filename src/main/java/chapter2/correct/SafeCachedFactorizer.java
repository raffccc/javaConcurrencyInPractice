package chapter2.correct;

import java.math.BigInteger;

public class SafeCachedFactorizer {
    
    private BigInteger lastNumber;
    private BigInteger[] lastFactors;
    private long hits;
    private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHits/ (double) hits;
    }
    
    public BigInteger[] factorize(BigInteger number) {
        synchronized (this) {
            ++hits;
            if (number.equals(lastNumber)) {
                ++cacheHits;
                return lastFactors.clone();
            }
        }

        BigInteger[] factors = factor(number);
        synchronized (this) {
            lastNumber = number;
            lastFactors = factors;
        }
        return factors;
    }

    private BigInteger[] factor(BigInteger number) {
        try {
            //lengthy operation
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
        return new BigInteger[0];
    }

}
