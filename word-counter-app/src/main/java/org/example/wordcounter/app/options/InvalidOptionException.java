package org.example.wordcounter.app.options;

public class InvalidOptionException extends RuntimeException {
    public InvalidOptionException(Throwable e) {
        super(e);
    }
}
