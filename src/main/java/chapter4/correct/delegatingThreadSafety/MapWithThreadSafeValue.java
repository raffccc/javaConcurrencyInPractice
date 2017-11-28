package chapter4.correct.delegatingThreadSafety;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import chapter4.shared.Point;
import chapter4.shared.SafePoint;

/**
 * All access to state is managed by {@link ConcurrentHashMap}, all the values of the map are thread safe
 * 
 * There is no use of any explicit synchronization.
 */
public class MapWithThreadSafeValue {
    
    private final ConcurrentMap<String, SafePoint> locations;
    private final Map<String, SafePoint> unmodifiableMap;
    
    public MapWithThreadSafeValue(Map<String, SafePoint> points) {
        this.locations = new ConcurrentHashMap<>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
    }

    /**
     * @return An unmodifiable but "live" view of the vehicle locations. 
     * 
     * If thread A calls getLocations and thread B later modifies the location of some of the points, those changes are reflected 
     * in the Map returned to thread A.
     * 
     * If an unchanging view of the fleet is required, this method could instead return a shallow copy of the locations map, because
     * the contentes of the Map are immutable.
     */
    public Map<String, SafePoint> getLocations() {
        return unmodifiableMap;
    }
    
    public SafePoint getLocation(String id) {
        return locations.get(id);
    }
    
    public void setLocation(String id, int x, int y) {
        if (!locations.containsKey(id)) {
            throw new IllegalArgumentException("invalid vehicle name: " + id);
        }
        
        locations.get(id).set(x, y);
    }
    
}
