package org.example.wordcounter.app.cli.options;

/**
 * Thrown when a cli option is invalid
 */
public class InvalidOptionException extends RuntimeException {

    public InvalidOptionException(String message) {
        super(message);
    }

    public InvalidOptionException(Throwable cause) {
        super(cause);
    }

}
