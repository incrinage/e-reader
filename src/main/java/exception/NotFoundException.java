package exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
