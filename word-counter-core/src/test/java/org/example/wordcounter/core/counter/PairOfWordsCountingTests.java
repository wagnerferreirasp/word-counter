package org.example.wordcounter.core.counter;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.example.wordcounter.core.counter.Constants.CZECH_ALPHABET;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PairOfWordsCountingTests {
    private final WordCounter wordCounter = new WordCounter(CZECH_ALPHABET);

    @Test
    public void emptyString_ShouldReturnEmptyMap() {
        String inputText = "";

        Map<String, Integer> expected = new HashMap<>();
        assertEquals(expected, wordCounter.countGroupsOfWords(2, inputText));
    }

    @Test
    public void wordAlone_ShouldReturnEmptyMap() {
        String inputText = "one";

        Map<String, Integer> expected = new HashMap<>();
        assertEquals(expected, wordCounter.countGroupsOfWords(2, inputText));
    }

    @Test
    public void pairOfWords_ShouldReturnThemOnce() {
        String inputText = "one two";

        Map<String, Integer> expected = new HashMap<>() {{
            put("one two", 1);
        }};
        assertEquals(expected, wordCounter.countGroupsOfWords(2, inputText));
    }

    @Test
    public void threeWords_ShouldReturnPairsOfNeighboursOnce() {
        String inputText = "you are beautiful";

        Map<String, Integer> expected = new HashMap<>() {{
            put("you are", 1);
            put("are beautiful", 1);
        }};
        assertEquals(expected, wordCounter.countGroupsOfWords(2, inputText));
    }

    @Test
    public void fourWords_ShouldReturnPairsOfNeighboursOnce() {
        String inputText = "you are beautiful are";

        Map<String, Integer> expected = new HashMap<>() {{
            put("you are", 1);
            put("are beautiful", 1);
            put("beautiful are", 1);
        }};
        assertEquals(expected, wordCounter.countGroupsOfWords(2, inputText));
    }

    @Test
    public void doubleYouAre_ShouldReturnOneDoubleOfIt() {
        String inputText = "you are beautiful you are";

        Map<String, Integer> expected = new HashMap<>() {{
            put("you are", 2);
            put("are beautiful", 1);
            put("beautiful you", 1);
        }};
        assertEquals(expected, wordCounter.countGroupsOfWords(2, inputText));
    }

    @Test
    public void doubleYouAreWithSpecialChars_ShouldReturnOneDoubleOfIt() {
        String inputText = "-you are, beautiful, \"you are\"";

        Map<String, Integer> expected = new HashMap<>() {{
            put("you are", 2);
            put("are beautiful", 1);
            put("beautiful you", 1);
        }};
        assertEquals(expected, wordCounter.countGroupsOfWords(2, inputText));
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

        Map<String, Integer> expected = new HashMap<>() {{
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
        assertEquals(expected, wordCounter.countGroupsOfWords(2, text));
    }
}
