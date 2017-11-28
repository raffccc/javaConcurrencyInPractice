package chapter16.correct;

import chapter16.Resource;

public class SafeLazyInitialization {
    
    private static Resource resource;
    
    public synchronized static Resource getInstance() {
        if (resource == null) {
            resource = new Resource();
        }
        return resource;
    }
    
}
