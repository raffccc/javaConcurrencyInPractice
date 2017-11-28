package chapter3.incorrect;

import chapter3.shared.Holder;

public class UnsafePublication {
    
    // Other threads can see null here or an older value, or worse, up-todate value for holder reference, bu stale values for 
    // the state of the Holder
    public Holder holder;
    
    public void initialize() {
        //If a thread other than the publishing thread were to call Holder.assertSanity, it could throw AssertionError 
        holder = new Holder(42);
    }
    
}
