package org.example.wordcounter.core.domain;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.example.wordcounter.core.domain.Constants.CZECH_ALPHABET;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PairOfWordsCountingTests {
    private final WordCounter wordCounter = new WordCounter(CZECH_ALPHABET);

    @Test
    public void emptyString_ShouldReturnEmptyMap() {
        Map<String, Integer> expected = new HashMap<>();
        assertEquals(expected, wordCounter.countGroupsOfWords(2,""));

        ArrayList<Optional<String>> optionals = new ArrayList<>();
        optionals.add(Optional.empty());
        optionals.add(Optional.of(""));

        optionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(System.out::println);
    }

    @Test
    public void wordAlone_ShouldReturnEmptyMap() {
        Map<String, Integer> expected = new HashMap<>();
        assertEquals(expected, wordCounter.countGroupsOfWords(2,"one"));
    }

    @Test
    public void pairOfWords_ShouldReturnThemOnce() {
        Map<String, Integer> expected = new HashMap<String, Integer>() {{
            put("one two", 1);
        }};
        assertEquals(expected, wordCounter.countGroupsOfWords(2,"one two"));
    }

    @Test
    public void threeWords_ShouldReturnPairsOfNeighboursOnce() {
        Map<String, Integer> expected = new HashMap<String, Integer>() {{
            put("you are", 1);
            put("are beautiful", 1);
        }};
        assertEquals(expected, wordCounter.countGroupsOfWords(2,"you are beautiful"));
    }

    @Test
    public void fourWords_ShouldReturnPairsOfNeighboursOnce() {
        Map<String, Integer> expected = new HashMap<String, Integer>() {{
            put("you are", 1);
            put("are beautiful", 1);
            put("beautiful are", 1);
        }};
        assertEquals(expected, wordCounter.countGroupsOfWords(2,"you are beautiful are"));
    }

    @Test
    public void doubleYouAre_ShouldReturnOneDoubleOfIt() {
        Map<String, Integer> expected = new HashMap<String, Integer>() {{
            put("you are", 2);
            put("are beautiful", 1);
            put("beautiful you", 1);
        }};
        assertEquals(expected, wordCounter.countGroupsOfWords(2,"you are beautiful you are"));
    }

    @Test
    public void doubleYouAreWithSpecialChars_ShouldReturnOneDoubleOfIt() {
        Map<String, Integer> expected = new HashMap<String, Integer>() {{
            put("you are", 2);
            put("are beautiful", 1);
            put("beautiful you", 1);
        }};
        assertEquals(expected, wordCounter.countGroupsOfWords(2,"-you are, beautiful, \"you are\""));
    }

    @Test
    public void complexText_ShouldCountCorrectly() {
        String text = "1\n" +
                "00:00:08,372 --> 00:00:11,966\n" +
                "Zrušená svatba\n" +
                "\n" +
                "2\n" +
                "00:00:47,882 --> 00:00:49,738\n" +
                "Czech Subtitles and Ripp by\n" +
                "Staz\n" +
                "\n" +
                "3\n" +
                "00:00:49,966 --> 00:00:52,988\n" +
                "O nic nejde.\n" +
                "Je to jen kluk od nás z práce.\n";

        Map<String, Integer> expected = new HashMap<String, Integer>() {{
            put("Zrušená svatba", 1);
            put("svatba Czech", 1);
            put("Czech Subtitles", 1);
            put("Subtitles and", 1);
            put("and Ripp", 1);
            put("Ripp by", 1);
            put("by Staz", 1);
            put("Staz O", 1);
            put("O nic", 1);
            put("nic nejde", 1);
            put("nejde Je", 1);
            put("Je to", 1);
            put("to jen", 1);
            put("jen kluk", 1);
            put("kluk od", 1);
            put("od nás", 1);
            put("nás z", 1);
            put("z práce", 1);
        }};
        assertEquals(expected, wordCounter.countGroupsOfWords(2,text));
    }
}
