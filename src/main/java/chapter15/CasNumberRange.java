package chapter15;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class CasNumberRange {
    
    private static class IntPair {
        final int lower;
        final int upper;

        public IntPair(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IntPair intPair = (IntPair) o;
            return lower == intPair.lower &&
                    upper == intPair.upper;
        }

        @Override
        public int hashCode() {
            return Objects.hash(lower, upper);
        }
    }
    
    private final AtomicReference<IntPair> values = new AtomicReference<>(new IntPair(0, 0));
    
    public int getLower() {
        return values.get().lower;
    }
    
    public void setLower(int i) {
        while (true) {
            IntPair oldv = values.get();
            if (i > oldv.upper) {
                throw new IllegalArgumentException("Can't set lower to " + i + " > upper");
            }
            
            IntPair newv = new IntPair(i, oldv.upper);
            if (values.compareAndSet(oldv, newv)) {
                return;
            }
        }
    }

    public int getUpper() {
        return values.get().upper;
    }

    public void setUpper(int i) {
        while (true) {
            IntPair oldv = values.get();
            if (i < oldv.lower) {
                throw new IllegalArgumentException("Can't set upper to " + i + " < lower");
            }

            IntPair newv = new IntPair(oldv.lower, i);
            if (values.compareAndSet(oldv, newv)) {
                return;
            }
        }
    }
    
}