package chapter3.incorrect;

public class NoVisibility {
    
    private static boolean ready;
    private static int number;
    
    private static class ReaderThread extends Thread {
        /*
         * It is possible that this method will:
         * 
         * 1 - Never Terminate: The value of "ready" might never become visible to the reader thread
         * 2 - Print 0: The write to ready might be made visible to this thread before the write to number.
         */
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        
        //Because there is no proper use of synchronization there is no guarantee that the values of ready and number written here
        //will be visible to the reader thread.
        number = 42;
        ready = true;
    }
    
}
