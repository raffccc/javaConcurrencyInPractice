package chapter5.correct.desktopsearch;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * Searches for files that are not yet indexed, adding all the non-indexed files to a {@link BlockingQueue} that is shared
 * with an {@link Indexer}, which is responsible for indexing the files.
 */
public class FileCrawler implements Runnable {
    
    private final BlockingQueue<File> fileQueue;
    private final FileFilter fileFilter;
    private final File root;
    
    public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }
    
    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles(fileFilter);
        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(entry);
                } else if (!alreadyIndexed(entry)) {
                    fileQueue.put(entry);
                }
            }
        }
        
    }

    private boolean alreadyIndexed(File entry) {
        return false;
    }

}
