package org.example.wordcounter.core.domain;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

class TextFrequenciesStorage {
    private final HashMap<String, Integer> textFrequency = new HashMap<>();

    public void addFrequency(@NotNull final String text) {
        if (textDoesNotExist(text)) {
            initializeFrequency(text);
        }
        incrementFrequency(text);
    }

    public Map<String, Integer> toMap() {
        return textFrequency;
    }

    private boolean textDoesNotExist(final String text) {
        return textFrequency.get(text) == null;
    }

    private void initializeFrequency(final String text) {
        textFrequency.put(text, 0);
    }

    private void incrementFrequency(final String text) {
        int quantity = textFrequency.get(text);
        textFrequency.put(text, quantity + 1);
    }
}

