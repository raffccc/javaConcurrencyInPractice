package chapter2.incorrect;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

public class UnsafeCachedFactorizer {
    
    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();
    
    public BigInteger[] factorize(BigInteger number) {
        if (number.equals(lastNumber.get())) {
            //This can return an incorrect value. If the thread gets interrupted while executing this block and another thread
            //modifies the value of lastFactors, when the first thread resume the execution, the value will be incorrect.
            return lastFactors.get();
        } else {
            BigInteger[] factors = factor(number);
            lastNumber.set(number);
            lastFactors.set(factors);
            return factors;
        }
    }

    private BigInteger[] factor(BigInteger number) {
        //factor code
        return new BigInteger[0];
    }

}
