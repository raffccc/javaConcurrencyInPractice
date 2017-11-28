package chapter16.correct;

import java.util.HashMap;
import java.util.Map;

/**
 * This class relies on immutability in order to share its state
 */
public class SafeStates {
    
    private final Map<String, String> states;
    
    public SafeStates() {
        states = new HashMap<>();
        states.put("paraiba", "PB");
        states.put("pernambuco", "PE");
        // ...
    }
    
    public String getAbbreviation(String s) {
        return states.get(s);
    }
    
}
