# Word Counter

This is an project that helps you count the occurrences of words in a text.
Given an alphabet, it can give you the counting occurrences for each word of the text you want.


## Single Words
You can count ocurrences of single words by using the method WordCounter::countWords.
Example:

```java
private final String MY_ALPHABET = "\\p{Alpha}";
private final WordCounter wordCounter = new WordCounter(MY_ALPHABET);

@Test
public void complexPhraseWithSpecialSymbols_ShouldIgnoreThem() {
    Map<String, Integer> expectedResult = new HashMap<String, Integer>() {{
        put("teste", 2);
        put("testf", 1);
        put("testg", 1);
        put("ASD", 1);
        put("ASDe", 1);
    }};
    assertEquals(expectedResult, wordCounter.countWords("   teste\r\n ->...[].(ASD)_ASDe  testf  \nteste  testg "));
}
```

## Pair of Words
You can also use it to count pairs of neighbour words in the text, by using the method WordCounter::countPairOfWords. 
Example:

```java
private final String MY_ALPHABET = "\\p{Alpha}";
private final WordCounter wordCounter = new WordCounter(MY_ALPHABET);

@Test
public void doubleYouAreWithSpecialChars_ShouldReturnOneDoubleOfIt() {
    Map<String, Integer> expected = new HashMap<String, Integer>() {{
        put("you are", 2);
        put("are beautiful", 1);
        put("beautiful you", 1);
    }};
    assertEquals(expected, wordCounter.countPairOfWords("-you are, beautiful, \"you are\""));
}
```
