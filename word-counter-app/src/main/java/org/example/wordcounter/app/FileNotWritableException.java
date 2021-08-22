package org.example.wordcounter.app;

public class FileNotWritableException extends RuntimeException {
	public FileNotWritableException(Throwable cause) {
		super(cause);
	}
}
