package chapter3.correct;

import java.math.BigInteger;
import java.util.Arrays;

public class ImmutableObjectExample {
    
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;


    public ImmutableObjectExample(BigInteger number, BigInteger[] factors) {
        this.lastNumber = number;
        this.lastFactors = Arrays.copyOf(factors, factors.length);
    }
    
    public BigInteger[] getFactors(BigInteger number) {
        if (lastNumber == null || !lastNumber.equals(number)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactors, lastFactors.length);
        }
    }
    
}
