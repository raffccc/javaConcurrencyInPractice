package chapter3.incorrect;

import java.util.EventListener;

import chapter3.shared.EventSource;

public class ThisEscape {
    
    public ThisEscape(EventSource source) {
        source.registerLisneter(new EventListener() {
            /*
             * This inner class contains a reference to ThisEscape
             * An object is in a predictable, consistent state only after it constructor returns, so publishing an object from within
             * its constructor can publish an incompletely object.
             */
        });
    }
    
}
