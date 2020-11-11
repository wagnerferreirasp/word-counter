package org.example.wordcounter.core.domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.example.wordcounter.core.domain.Constants.CZECH_ALPHABET;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TripletOfWordsTests {
    private final WordCounter wordCounter = new WordCounter(CZECH_ALPHABET);

    @Test
    void emptyText_ShouldBeEmptyMap() {
        Map<String, Integer> expected = Collections.emptyMap();
        assertEquals(expected, wordCounter.countGroupsOfWords(3,""));
    }

    @Test
    void singleWord_ShouldBeEmptyMap() {
        Map<String, Integer> expected = Collections.emptyMap();
        assertEquals(expected, wordCounter.countGroupsOfWords(3,"test"));
    }

    @Test
    void pairOfWords_ShouldBeEmptyMap() {
        Map<String, Integer> expected = Collections.emptyMap();
        assertEquals(expected, wordCounter.countGroupsOfWords(3,"testa testb"));
    }

    @Test
    void tripletOfWords_ShouldBeOneOfIt() {
        Map<String, Integer> expected = new HashMap<String, Integer>() {{
           put("testa testb testc", 1);
        }};
        assertEquals(expected, wordCounter.countGroupsOfWords(3,"testa testb testc"));
    }

    @Test
    void tripletOfWordsTwice_ShouldBeTwoOfIt() {
        Map<String, Integer> expected = new HashMap<String, Integer>() {{
            put("testa testb testc", 2);
            put("testb testc testa", 1);
            put("testc testa testb", 1);
        }};
        assertEquals(expected, wordCounter.countGroupsOfWords(3,"testa testb testc testa testb testc"));
    }
}
