package chapter6.warn;

import util.ExceptionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * Uses some parallelism to download the images from a page, but we can do better. 
 * 
 * There is no need for users to wait for all the images to be downloaded, it is better to show individual images as 
 * they become available.
 */
public class FutureRenderer {
    
    private final ExecutorService executor = Executors.newFixedThreadPool(3);
    
    void renderPage(CharSequence source) {
        final List<String> imageInfos = scanForImageInfo(source);

        Callable<List<String>> task =
                new Callable<List<String>>() {
                    @Override
                    public List<String> call() throws Exception {
                        List<String> result = new ArrayList<>();
                        for (String imageInfo : imageInfos) {
                            result.add(downloadData(imageInfo));
                        }
                        return result;
                    }

                    private String downloadData(String imageInfo) {
                        return "";
                    }
                };
        
        Future<List<String>> future = executor.submit(task);
        renderText(source);

        List<String> imagesData = null;
        try {
            imagesData = future.get();
            
            for (String imageData : imagesData) {
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            // Re-assert the thread's interrupted status
            Thread.currentThread().interrupt();
            
            // We don' need the result, so cancel the task too
            future.cancel(true);
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
