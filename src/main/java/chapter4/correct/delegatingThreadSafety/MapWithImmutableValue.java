package chapter4.correct.delegatingThreadSafety;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import chapter4.shared.Point;

/**
 * All access to state is managed by {@link java.util.concurrent.ConcurrentHashMap}, and all the keys and values of the
 * {@link java.util.Map} are immutable.
 * 
 * There is no use of any explicit synchronization.
 */
public class MapWithImmutableValue {
    
    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;
    
    public MapWithImmutableValue(Map<String, Point> points) {
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
     * the contents of the Map are immutable.
     */
    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }
    
    public Point getLocation(String id) {
        return locations.get(id);
    }
    
    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null) {
            throw new IllegalArgumentException("invalid vehicle name: " + id);
        }
    }
    
}
