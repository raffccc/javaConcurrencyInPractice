package chapter13.correct;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AvoidLockOrderingDeadlock {
    
    public boolean transferMoney(Account fromAcct,
                                 Account toAcct,
                                 BigDecimal amount,
                                 long timeout,
                                 TimeUnit unit) throws InterruptedException {
        
        long stopTime = System.nanoTime() + unit.toNanos(timeout);
        
        while (true) {
            if (fromAcct.lock.tryLock()) {
                try {
                    if (toAcct.lock.tryLock()) {
                        try {
                            if (fromAcct.getBalance().compareTo(amount) < 0) {
                                throw new IllegalArgumentException();
                            }
                            
                            fromAcct.debit(amount);
                            toAcct.credit(amount);
                            return true;
                        } finally {
                            toAcct.lock.unlock();
                        }
                    }
                } finally {
                    fromAcct.lock.unlock();
                }
            }
            
            if (System.nanoTime() > stopTime) {
                return false;
            }
            
            final long fixedDelay = 100;
            final long randMod = 3;
            Random random = new Random();
            TimeUnit.NANOSECONDS.sleep(fixedDelay + random.nextLong() % randMod);
        }
    }
    
    private class Account {
        private Lock lock = new ReentrantLock();
        private BigDecimal balance;

        public BigDecimal getBalance() {
            return balance;
        }

        public void debit(BigDecimal amount) {
            this.balance = this.balance.subtract(amount);
        }

        public void credit(BigDecimal amount) {
            this.balance = this.balance.add(amount);
        }
    }
    
}
