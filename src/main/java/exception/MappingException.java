package exception;

public class MappingException extends RuntimeException {
    public MappingException(String msg) {
        super(msg);
    }

    public MappingException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
