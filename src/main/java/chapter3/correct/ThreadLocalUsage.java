package chapter3.correct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Each thread will have its own connection
 */
public class ThreadLocalUsage {
    
    private static ThreadLocal<Connection> connectionHolder = ThreadLocal.withInitial(() -> {
        try {
            return DriverManager.getConnection("fakeUrl");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    });
    
    public static Connection getConnection() {
        return connectionHolder.get();
    }
    
}
