package chapter4.shared;

/**
 * Created by rafcar on 6/17/17.
 */
public class SafePoint {

    private int x, y;
    
    private String string = "";

    /**
     * This private constructor exists to avoid the race condition that would occur if the copy constructor were implemented as
     * this(p.x, p.y); this is an example of the private constructor capture idiom.
     */
    private SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafePoint(SafePoint p) {
        this(p.get());
    }

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized int[] get() {
        return new int[]{x, y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
