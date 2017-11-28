package chapter4.correct.delegatingThreadSafety;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Delegates thread safety to more than one underlying state variable. Those underlying state variables are independent,
 * meaning that this class doesn't impose any invariants involving the multiple state variables.
 * 
 * {@link CopyOnWriteArrayList} is a thread-safe {@link List} implementation particularly suited for managing listener lists.
 */
public class MultipleDelegation {
    
    private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<>();
    private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();
    
    public void addKeyListener(KeyListener listener) {
        keyListeners.add(listener);
    }

    public void addMouseListener(MouseListener listener) {
        mouseListeners.add(listener);
    }

    public void removeKeyListener(KeyListener listener) {
        keyListeners.remove(listener);
    }

    public void removeMouseListener(MouseListener listener) {
        mouseListeners.remove(listener);
    }
    
}
