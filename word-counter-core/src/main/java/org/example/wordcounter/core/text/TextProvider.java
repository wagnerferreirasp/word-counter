package org.example.wordcounter.core.text;

import java.util.List;

/**
 * Provides texts. It can be implemented to provide from a database or from files, for example
 */
public interface TextProvider {
    /**
     * @return all texts to be provided
     */
    List<Text> findAll();
}
