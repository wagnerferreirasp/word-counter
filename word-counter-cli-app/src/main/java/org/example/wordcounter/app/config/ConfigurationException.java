package org.example.wordcounter.app.config;

/**
 * Thrown when a configuration option is invalid to execute the application
 * @see ApplicationConfig
 */
public class ConfigurationException extends RuntimeException {

    public ConfigurationException(String message) {
        super(message);
    }
}
