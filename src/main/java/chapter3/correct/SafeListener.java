package chapter3.correct;

import java.util.EventListener;

import chapter3.shared.EventSource;

public class SafeListener {
    
    private final EventListener listener;
    
    private SafeListener() {
        listener = new EventListener() {
        };
    }

    /**
     * Using this factory methods avoid the improper instantiation of the object as seen in 
     * {@link chapter3.incorrect.ThisEscape} 
     */
    public static SafeListener newInstance(EventSource source) {
        SafeListener safe = new SafeListener();
        source.registerLisneter(safe.listener);
        return safe;
    }
    
}
