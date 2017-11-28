package chapter5.correct.desktopsearch;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * Consumes the pending-to-index files and indexes them.
 */
public class Indexer implements Runnable {
    
    private final BlockingQueue<File> queue;
    
    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                indexFile(queue.take());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    } 

    private void indexFile(File take) {
        System.out.println("Indexing file " + take.getAbsolutePath());
    }
    
}
