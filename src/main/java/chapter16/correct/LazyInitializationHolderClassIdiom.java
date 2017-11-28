package chapter16.correct;

import chapter16.Resource;

/**
 * Uses a classs whose only purpose is to initialize the Resource.
 * 
 * The JVM defers initializing the ResourceHolder class until it is actually used. Because Resource is initialized
 * with a static initializer, no additional synchronization is needed.
 */
public class LazyInitializationHolderClassIdiom {
    
    public static Resource getResource() {
        return ResourceHolder.resource;
    }
    
    private static class ResourceHolder {
        public static Resource resource = new Resource();
    }
    
}
