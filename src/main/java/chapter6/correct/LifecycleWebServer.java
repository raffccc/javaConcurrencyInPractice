package chapter6.correct;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * Uses information about the lifecycle of the ExecutorService
 */
public class LifecycleWebServer {

    public static final int N_THREADS = 10;
    private final ExecutorService exec = Executors.newFixedThreadPool(N_THREADS);
    
    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (!exec.isShutdown()) {
            try {
                final Socket connection = socket.accept();
                Runnable task = () -> handleRequest(connection);
                exec.execute(task);
            } catch (RejectedExecutionException e) {
                if (!exec.isShutdown()) {
                    System.out.println("task submission rejected" + e);
                }
            }
        }
    }
    
    public void stop() {
        exec.shutdown();
    }

    private void handleRequest(Socket connection) {
        System.out.println("Handling " + connection);
    }
    
}