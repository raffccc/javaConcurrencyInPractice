package chapter9;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.*;

/**
 * This class is responsible for tracking progress of long running tasks
 */
public abstract class BackgroundTask<V> implements Runnable, Future<V> {
    
    private final FutureTask<V> computation = new Computation();

    @Override
    public void run() {
        
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return computation.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return computation.isCancelled();
    }

    @Override
    public boolean isDone() {
        return computation.isDone();
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return computation.get();
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return computation.get(timeout, unit);
    }

    private class Computation extends FutureTask<V> {
        
        public Computation() {
            super(() -> BackgroundTask.this.compute());
        }

        @Override
        protected void done() {
            Runnable runnable = () -> {
                V value = null;
                Throwable thrown = null;
                boolean cancelled = false;
                try {
                    value = get();
                } catch (InterruptedException consumed) {
                } catch (ExecutionException e) {
                    thrown = e.getCause();
                } catch (CancellationException e) {
                    cancelled = true;
                } finally {
                    onCompletion(value, thrown, cancelled);
                }
            };

            // Runs this runnable in the event thread
        }
    }
    
    protected void setProgress(final int current, final int max) {
        Runnable runnable = () -> onProgress(current, max);
        
        // Runs this runnable in the event thread
    }

    // Called in the background thread, this method can call setProgress
    protected abstract V compute();
    
    // Called in the event thread
    protected void onCompletion(V result, Throwable exception, boolean cancelled) { }
    
    protected void onProgress(int current, int max) { }
    
    // Other future methods forwarded to computation

    /**
     * Example of executing the BackgroundTask
     */
    public static void main(String[] args) {
        JButton startButton = new JButton("Start");
        JButton cancelButton = new JButton("Cancel");
        
        Executor backgroundExecutor = Executors.newCachedThreadPool();
        
        startButton.addActionListener(e -> {
            class CancelListener implements ActionListener {
                BackgroundTask<?> task;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (task != null) {
                        task.cancel(true);
                    }
                }
            }
            
            final CancelListener listener = new CancelListener();
            listener.task = new BackgroundTask<Void>() {
                @Override
                protected Void compute() {
                    while (!isCancelled()) {
                        // doSomeWork();
                    }
                    return null;
                }

                @Override
                protected void onCompletion(Void result, Throwable exception, boolean cancelled) {
                    cancelButton.removeActionListener(listener);
                }
            };
            
            cancelButton.addActionListener(listener);
            backgroundExecutor.execute(listener.task);
        });
    }
}
