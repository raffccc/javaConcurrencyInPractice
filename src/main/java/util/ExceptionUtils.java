package util;

public class ExceptionUtils {

    public static RuntimeException launderThrowable(Throwable e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }

        if (e instanceof Error) {
            throw (Error) e;
        }

        throw new IllegalStateException("Not unchecked", e);
    }
    
}
