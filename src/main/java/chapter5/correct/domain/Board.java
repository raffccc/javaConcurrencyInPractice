package chapter5.correct.domain;

/**
 * Class used in {@link chapter5.correct.CyclicBarrierExample}
 */
public class Board {
    
    private int maxX;
    private int maxY;
    
    public void commitNewValues() {
    }

    public Board getSubBoard(int numberOfSubboards, int subboardIndex) {
        return new Board();
    }

    public boolean hasConverged() {
        return false;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setNewValue(int x, int y, int i) {
    }

    public void waitForConvergence() {
    }
}
