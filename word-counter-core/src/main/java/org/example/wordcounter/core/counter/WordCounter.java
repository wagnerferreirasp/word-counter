package org.example.wordcounter.core.counter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Here is where the words get counted in a single text String.
 * It requires an alphabet, and will consider as words all strings that
 * contain letters from the alphabet. Depending on the group size, it will count groups of words as well
 */
public class WordCounter {
    private final String notAlphabetRegex;

    public WordCounter(final String alphabet) {
        this.notAlphabetRegex = "[^" + alphabet + "]";
    }

    /**
     * Gives the count for each word or group of words found in the text
     * @param numberOfWordsPerGroup the size of the group
     * @param text the text to be analyzed
     * @return the count for each group of words in the text
     */
    public Map<String, Integer> countGroupsOfWords(int numberOfWordsPerGroup, String text) {
        String[] words = getWords(text);
        TextFrequenciesStorage frequencies = new TextFrequenciesStorage();
        for (int i = 0; i <= words.length-numberOfWordsPerGroup; i++) {
            addFrequencyOfTheNextNWords(numberOfWordsPerGroup, i, words, frequencies);
        }
        return frequencies.toMap();
    }

    private String[] getWords(String text) {
        return Arrays.stream(text.split(notAlphabetRegex))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }

    private void addFrequencyOfTheNextNWords(int numberOfWordsPerGroup, int startIndex, String[] words, TextFrequenciesStorage frequencies) {
        String groupOfWords = words[startIndex];
        for (int j = 1; j < numberOfWordsPerGroup; j++) {
            groupOfWords = String.format("%s %s", groupOfWords, words[startIndex + j]);
        }
        frequencies.addFrequency(groupOfWords);
    }

}
