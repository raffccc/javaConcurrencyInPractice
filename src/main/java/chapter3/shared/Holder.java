package chapter3.shared;

public class Holder {
    
    //If this field was final, this class would be immune to improper publication
    private int n;
    
    public Holder(int n) {
        this.n = n;
    }
    
    public void assertSanity() {
        // This can happen if this object is unsafely published. A thread may see a stale value the first time it reads a field and 
        // then a more up-to-date value the next time
        if (n != n) {
            throw new AssertionError("This statement is false");
        }
    }
    
}
