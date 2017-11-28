package chapter6.correct;

import util.ExceptionUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * Improves the performance of the page renderer:
 *   - Shorter total runtime
 *   - Improved responsiveness
 *   - Parallelize the download of each image
 */
public class CompletionServiceRenderer {
    
    private final ExecutorService executor;
    
    public CompletionServiceRenderer(ExecutorService executor) {
        this.executor = executor;
    }
    
    public void render(CharSequence source) {
        final List<String> imageInfos = scanForImageInfo(source);

        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

        for (String imageInfo : imageInfos) {
            completionService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception { 
                    return downloadData(imageInfo);
                }

                private String downloadData(String imageInfo) {
                    return "";
                }
            });
        }
        
        renderText(source);

        try {
            for (int i = 0; i < imageInfos.size(); i++) {
                Future<String> f = completionService.take();
                String imageData = f.get();
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            // Re-assert the thread's interrupted status
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw ExceptionUtils.launderThrowable(e);
        }
    }

    private void renderImage(String imageData) {
        // TODO render image
    }

    private void renderText(CharSequence source) {
        // TODO write stuff
    }

    private List<String> scanForImageInfo(CharSequence source) {
        return Collections.emptyList();
    }


}