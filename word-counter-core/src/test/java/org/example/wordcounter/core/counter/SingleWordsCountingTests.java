package org.example.wordcounter.core.counter;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.example.wordcounter.core.counter.Constants.CZECH_ALPHABET;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleWordsCountingTests {
    private final WordCounter wordCounter = new WordCounter(CZECH_ALPHABET);
    
    @Test
    public void emptyText_ShouldBeEmptyMap() {
        Map<String, Integer> expectedResult = new HashMap<>();
        assertEquals(expectedResult, wordCounter.countGroupsOfWords(1,""));
    }

    @Test
    public void singleWord_ShouldReturnOneOfItSelf() {
        Map<String, Integer> expectedResult = new HashMap<String, Integer>() {{
           put("test", 1);
        }};
        assertEquals(expectedResult, wordCounter.countGroupsOfWords(1,"test"));
    }

    @Test
    public void singleWordWithComma_ShouldReturnOneOfItSelf() {
        Map<String, Integer> expectedResult = new HashMap<String, Integer>() {{
            put("test", 1);
        }};
        assertEquals(expectedResult, wordCounter.countGroupsOfWords(1,"test,"));
    }

    @Test
    public void singleWordWithUnderscoreAtTheBeginning_ShouldReturnOneOfItSelf() {
        Map<String, Integer> expectedResult = new HashMap<String, Integer>() {{
            put("test", 1);
        }};
        assertEquals(expectedResult, wordCounter.countGroupsOfWords(1,"_test"));
    }

    @Test
    public void singleWordWithDashAtBeginning_ShouldReturnOneOfItSelf() {
        Map<String, Integer> expectedResult = new HashMap<String, Integer>() {{
            put("test", 1);
        }};
        assertEquals(expectedResult, wordCounter.countGroupsOfWords(1,"-test"));
    }

    @Test
    public void duplicateWord_ShouldReturnOneOfItSelfTwice() {
        Map<String, Integer> expectedResult = new HashMap<String, Integer>() {{
            put("test", 2);
        }};
        assertEquals(expectedResult, wordCounter.countGroupsOfWords(1,"test test"));
    }

    @Test
    public void duplicateWordWithDoubleSpace_ShouldReturnOneOfItSelfTwice() {
        Map<String, Integer> expectedResult = new HashMap<String, Integer>() {{
            put("test", 2);
        }};
        assertEquals(expectedResult, wordCounter.countGroupsOfWords(1,"test  test"));
    }

    @Test
    public void singleWordWithSpacesBeforeAndAfter_ShouldReturnOneOfItself() {
        Map<String, Integer> expectedResult = new HashMap<String, Integer>() {{
            put("test", 1);
        }};
        assertEquals(expectedResult, wordCounter.countGroupsOfWords(1," test "));
    }

    @Test
    public void twoWords_ShouldReturnOneOfEach() {
        Map<String, Integer> expectedResult = new HashMap<String, Integer>() {{
            put("teste", 1);
            put("testf", 1);
        }};
        assertEquals(expectedResult, wordCounter.countGroupsOfWords(1,"teste testf"));
    }

    @Test
    public void threeWords_ShouldReturnOneOfEach() {
        Map<String, Integer> expectedResult = new HashMap<String, Integer>() {{
            put("teste", 1);
            put("testf", 1);
            put("testg", 1);
        }};
        assertEquals(expectedResult, wordCounter.countGroupsOfWords(1,"teste testf testg"));
    }

    @Test
    public void twoWordsWithLineEndingSeparator_ShouldReturnOneOfEach() {
        Map<String, Integer> expectedResult = new HashMap<String, Integer>() {{
            put("teste", 1);
            put("testf", 1);
        }};
        assertEquals(expectedResult, wordCounter.countGroupsOfWords(1,"teste\ntestf"));
    }

    @Test
    public void twoWordsWithWindowsLineEndingSeparator_ShouldReturnOneOfEach() {
        Map<String, Integer> expectedResult = new HashMap<String, Integer>() {{
            put("teste", 1);
            put("testf", 1);
        }};
        assertEquals(expectedResult, wordCounter.countGroupsOfWords(1,"teste\r\ntestf"));
    }

    @Test
    public void czechWord_ShouldReturnOneOfItself() {
        Map<String, Integer> expectedResult = new HashMap<String, Integer>() {{
            put("Příští", 1);
        }};
        assertEquals(expectedResult, wordCounter.countGroupsOfWords(1,"Příští"));
    }

    @Test
    public void complexPhraseWithSpecialSymbols_ShouldIgnoreThem() {
        Map<String, Integer> expectedResult = new HashMap<String, Integer>() {{
            put("teste", 2);
            put("testf", 1);
            put("testg", 1);
            put("ASD", 1);
            put("ASDe", 1);
        }};
        assertEquals(expectedResult, wordCounter.countGroupsOfWords(1,"   teste\r\n ->...[].(ASD)_ASDe  testf  \nteste  testg "));
    }

    @Test
    public void complexPart_ShouldCountCorrectly() {
        String text = "4\n" +
                "00:00:14,432 --> 00:00:17,727\n" +
                "Děti, předtím než jsem potkal vaší mámu,\n" +
                "když jsem byl stále venku a hledal,";

        Map<String, Integer> expectedResult = new HashMap<String, Integer>() {{
            put("Děti", 1);
            put("předtím", 1);
            put("jsem", 2);
            put("potkal", 1);
            put("vaší", 1);
            put("mámu", 1);
            put("když", 1);
            put("byl", 1);
            put("stále", 1);
            put("venku", 1);
            put("a", 1);
            put("hledal", 1);
            put("než", 1);
        }};
        assertEquals(expectedResult, wordCounter.countGroupsOfWords(1,text));
    }

    @Test
    public void complex2_ShouldCountCorrectly() {
        String text = "4\n" +
                "00:00:14,682 --> 00:00:17,226\n" +
                "Když\rjste nezadaní a vaši kamarádi se žačnou ženit,\n" +
                "\n" +
                "5\n" +
                "00:00:17,310 --> 00:00:21,147\n" +
                "každá pozvánka na svatbu představuje zvláštní\n" +
                "moment sebeohodnocení.\n" +
                "\n" +
                "6\n" +
                "00:00:21,439 --> 00:00:24,358\n" +
                "\"Budete sebou někoho brát nebo půjdete sami?\"\n" +
                "\n" +
                "7\n" +
                "00:00:24,692 --> 00:00:26,319\n" +
                "Skutečná otázka ale je\n" +
                "\n" +
                "8\n" +
                "00:00:26,444 --> 00:00:28,362\n" +
                "\"Kde se vidíte že budete za tři měsíce?\n" +
                "\n" +
                "9\n" +
                "00:00:28,488 --> 00:00:31,657\n" +
                "Budete sedět vedle svý holky nebo balit družičky. \"\n" +
                "\n" +
                "10\n" +
                "00:00:32,158 --> 00:00:34,619\n" +
                "Já vždycky zaškrtával že sebou beru hosta.\n" +
                "\n" +
                "11\n" +
                "00:00:35,119 --> 00:00:36,078\n" +
                "Byl sem optimista.";

        Map<String, Integer> expectedResult = new HashMap<String, Integer>() {{
            put("Když", 1);
            put("jste", 1);
            put("nezadaní", 1);
            put("a", 1);
            put("vaši", 1);
            put("kamarádi", 1);
            put("se", 2);
            put("žačnou", 1);
            put("ženit", 1);
            put("každá", 1);
            put("pozvánka", 1);
            put("na", 1);
            put("svatbu", 1);
            put("představuje", 1);
            put("zvláštní", 1);
            put("moment", 1);
            put("sebeohodnocení", 1);
            put("Budete", 2);
            put("sebou", 2);
            put("někoho", 1);
            put("brát", 1);
            put("nebo", 2);
            put("půjdete", 1);
            put("sami", 1);
            put("Skutečná", 1);
            put("otázka", 1);
            put("ale", 1);
            put("je", 1);
            put("Kde", 1);
            put("vidíte", 1);
            put("že", 2);
            put("budete", 1);
            put("za", 1);
            put("tři", 1);
            put("měsíce", 1);
            put("sedět", 1);
            put("vedle", 1);
            put("svý", 1);
            put("holky", 1);
            put("balit", 1);
            put("družičky", 1);
            put("Já", 1);
            put("vždycky", 1);
            put("zaškrtával", 1);
            put("beru", 1);
            put("hosta", 1);
            put("Byl", 1);
            put("sem", 1);
            put("optimista", 1);
        }};
        assertEquals(expectedResult, wordCounter.countGroupsOfWords(1,text));
    }
}
