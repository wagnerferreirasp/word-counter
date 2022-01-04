package org.example.wordcounter.core.counter;

import org.example.wordcounter.core.text.Text;
import org.example.wordcounter.core.text.TextProvider;
import org.example.wordcounter.core.util.MapUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordRankingService {
    private final WordCounter wordCounter;
    private final TextProvider textProvider;

    public WordRankingService(String alphabet, TextProvider textProvider) {
        this.wordCounter = new WordCounter(alphabet);
        this.textProvider = textProvider;
    }

    public LinkedHashMap<String, Integer> rankWordsFromTexts(int groupSize) {
        Map<String, Integer> totalCountMap = new HashMap<>();
        for (Text text : textProvider.findAll()) {
            Map<String, Integer> countMap = countWordsInTextWithLowerCase(text, groupSize);
            totalCountMap = MapUtils.sumMaps(totalCountMap, countMap);
        }
        return MapUtils.sortedMapByValue(totalCountMap);
    }

    private Map<String, Integer> countWordsInTextWithLowerCase(Text text, int groupSize) {
        String textContent = text.getContent().toLowerCase();
        return wordCounter.countGroupsOfWords(groupSize, textContent);
    }
}
