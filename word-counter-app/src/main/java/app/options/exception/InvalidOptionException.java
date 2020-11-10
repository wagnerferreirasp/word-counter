package app.options.exception;

public class InvalidOptionException extends RuntimeException {
    public InvalidOptionException(Throwable e) {
        super(e);
    }
}
