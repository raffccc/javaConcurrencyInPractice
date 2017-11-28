package chapter10.incorrect;

public class LeftRightDeadlock {
    
    private final Object left = new Object();
    private final Object right = new Object();
    
    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                System.out.println("Hi");
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                System.out.println("Hello");
            }
        }
    }
    
}
