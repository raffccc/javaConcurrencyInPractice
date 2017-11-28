package chapter5.correct.desktopsearch;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by rafcar on 6/28/17.
 */
public class FileIndexingExecutor {

    private static final int BOUND = 500;
    private static final int N_CONSUMERS = 20;
    
    public static void startIndexing(File[] roots) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(BOUND);

        FileFilter filter = pathname -> true;

        for (File root : roots) {
            new Thread(new FileCrawler(queue, filter, root)).start();
        }

        for (int i = 0; i < N_CONSUMERS; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }
    
}
