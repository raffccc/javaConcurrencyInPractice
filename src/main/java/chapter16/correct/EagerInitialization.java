package chapter16.correct;

import chapter16.Resource;

public class EagerInitialization {
    
    private static Resource resource = new Resource();
    
    public synchronized static Resource getInstance() {
        return resource;
    }
    
}