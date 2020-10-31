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
        return countGroupsOfWords(text, 1);
    }

    public Map<String, Integer> countPairsOfWords(@NotNull final String text) {
        return countGroupsOfWords(text, 2);
    }

    public Map<String, Integer> countTripletsOfWords(@NotNull final String text) {
        return countGroupsOfWords(text, 3);
    }

    private String[] getWords(String text) {
        return Arrays.stream(text.split(notAlphabet))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }

    private Map<String, Integer> countGroupsOfWords(String text, int numberOfWordsPerGroup) {
        String[] words = getWords(text);
        TextFrequenciesStorage frequencies = new TextFrequenciesStorage();
        for (int i = 0; i <= words.length-numberOfWordsPerGroup; i++) {
            addFrequencyOfTheNextNWords(numberOfWordsPerGroup, i, words, frequencies);
        }
        return frequencies.toMap();
    }

    private void addFrequencyOfTheNextNWords(int n, int startIndex, String[] words, TextFrequenciesStorage frequencies) {
        String groupOfWords = words[startIndex];
        for (int j = 1; j < n; j++) {
            groupOfWords = String.format("%s %s", groupOfWords, words[startIndex + j]);
        }
        frequencies.addFrequency(groupOfWords);
    }
}
