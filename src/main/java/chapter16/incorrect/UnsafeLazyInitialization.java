package chapter16.incorrect;

import chapter16.Resource;

public class UnsafeLazyInitialization {
    
    private static Resource resource;
    
    /*
     * Thread A first to invoke getInstance, instantiaties a new resource and sets resource to reference it
     * 
     * Thread B then runs and sees that resource variable is set, but there is no guarantee that B will see the
     * correct state of the Resource object. B could see the write to resource as occurring before the writes
     * to the fields of the Resource.
     * 
     */
    public static Resource getInstance() {
        if (resource == null) {
            resource = new Resource(); // unsafe publication
        }
        return resource;
    }
    
}
