package chapter5.correct;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import chapter5.correct.domain.Board;

/**
 * Allows a fixed number of parties to rendezvous repeatedly at a barrier point, useful in parallel iterative algorithms that break 
 * down a problem into a fixed number of independent subproblems. Threads call await when they reach the barrier point, and await 
 * blocks until all the threads have reached the barrier point.
 * 
 * If all threads meet at the barrier point, the barrier has been successfully passed, in which case all threads are released and 
 * the barrier is reset so it can be used again, await returns a unique arrival index for each thread, which can be used to elect a 
 * leader that takes some special action in the next iteration.
 * 
 * You can pass a Runnable to the constructor that gets executed by one of the worker threads when the barrier is passed but before 
 * the blocked threads are released.
 */
public class CyclicBarrierExample {
    
    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CyclicBarrierExample(Board board) {
        this.mainBoard = board;
        
        int count = Runtime.getRuntime().availableProcessors();
        
        this.barrier = new CyclicBarrier(count, () -> mainBoard.commitNewValues());
        
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++) {
            workers[i] = new Worker(mainBoard.getSubBoard(count, i));
        }
    }
    
    private class Worker implements Runnable {
        private final Board board;
        
        public Worker(Board board) {
            this.board = board;
        }
        
        public void run() {
            while (!board.hasConverged()) {
                for (int x = 0; x < board.getMaxX(); x++) {
                    for (int y = 0; y < board.getMaxY(); y++) {
                        board.setNewValue(x, y, computeValue(x, y));
                    }
                }

                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    return;
                }
            }
        }

        private int computeValue(int x, int y) {
            return 0;
        }
    }
    
    public void start() {
        for (int i = 0; i < workers.length; i++) {
            new Thread(workers[i]).start();
        }
        mainBoard.waitForConvergence();
    }
}
