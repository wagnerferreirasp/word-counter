package org.example.wordcounter.core.counter;

import org.example.wordcounter.core.text.Text;
import org.example.wordcounter.core.text.TextProvider;
import org.example.wordcounter.core.util.MapUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Use this class when the use case is not only counting words, but ranking them
 */
public class WordRankingService {

    private final WordCounter wordCounter;
    private final TextProvider textProvider;

    public WordRankingService(String alphabetRegex, TextProvider textProvider) {
        this.wordCounter = new WordCounter(alphabetRegex);
        this.textProvider = textProvider;
    }

    /**
     * Creates a ranking of words (or group of words) from the text provider
     * @param groupSize the number of words to count together
     * @return the ranking of occurrences of words (or group of words)
     */
    public LinkedHashMap<String, Integer> rankWordsFromTexts(int groupSize) {
        return MapUtils.sortedMapByValue(textProvider.findAll().stream()
                .map(text -> countWordsInTextWithLowerCase(text, groupSize))
                .reduce(new HashMap<>(), MapUtils::sumMaps)
        );
    }

    private Map<String, Integer> countWordsInTextWithLowerCase(Text text, int groupSize) {
        String textContentLower = text.getContent().toLowerCase();
        return wordCounter.countGroupsOfWords(groupSize, textContentLower);
    }

}
