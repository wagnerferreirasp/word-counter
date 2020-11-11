package org.example.wordcounter.core.usecases;

import org.example.wordcounter.core.boundaries.Text;
import org.example.wordcounter.core.boundaries.TextProvider;
import org.example.wordcounter.core.domain.WordCounter;
import org.example.wordcounter.core.util.MapUtils;

import java.util.*;

public class WordRankingUseCase {
    private final WordCounter wordCounter;
    private final TextProvider textProvider;

    public WordRankingUseCase(String alphabet, TextProvider textProvider) {
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
