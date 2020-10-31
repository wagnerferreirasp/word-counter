package core;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Map;

public class WordCounter {
    private final String notAlphabet;

    public WordCounter(@NotNull final String alphabet) {
        this.notAlphabet = "[^" + alphabet + "]";
    }

    public Map<String, Integer> countWords(@NotNull final String text) {
        TextFrequencyStorage textFrequencyStorage = new TextFrequencyStorage();
        for (String word : getWords(text)) {
            textFrequencyStorage.addFrequency(word);
        }
        return textFrequencyStorage.toMap();
    }

    public Map<String, Integer> countPairOfWords(@NotNull String text) {
        TextFrequencyStorage textFrequencyStorage = new TextFrequencyStorage();
        String[] words = getWords(text);
        for (int i = 0; i < words.length-1; i++) {
            String pairOfWords = words[i] + " " + words[i + 1];
            textFrequencyStorage.addFrequency(pairOfWords);
        }
        return textFrequencyStorage.toMap();
    }

    private String[] getWords(String text) {
        return Arrays.stream(text.split(notAlphabet))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }
}
