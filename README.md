# Word Counter

This is a project that helps you count the occurrences of words in a text.
Given an alphabet, it can give you the counting occurrences for each word of the text you want.

This is the core module, so it is fully covered by unit tests and does not deal with 
IO stuff, like reading files, neither with frameworks. 
To be able to use its functionality, you can create your application to use this module how you want.


# Use cases

## Ranking words
This use case is about ranking words from a list of texts. You can choose to rank single words, pair of words together, 
or any group size you want. For example, the following texts would have the following result of ranking:

Texts: 
```
"you are good"
"who you are"
```

Group size: 
```
2 // I want to count pairs of words
```

Expected result
```
"you are": 2 occurrences
"are good": 1 occurrence
"who you": 1 occurrence
```


### Configuration
The class to represent this use case is the `WordRankingUseCase`, with the following constructor:

```java
public WordRankingUseCase(String alphabet, TextProvider textProvider)
```

Where the
 - `alphabet` is the regex representation of the characters of the alphabet that you want to consider when counting words
 - `textProvider` is the way you are going to provide the texts as input, by implementing its interface TextProvider:
 
```java
public interface TextProvider {
    List<Text> findAll();
}
```

### Usage
To rank words in the provided texts, you can call the following method from the `WordRankingUseCase` class:
```java
public LinkedHashMap<String, Integer> rankWordsFromTexts(int groupSize);
```

You can choose how many words you want to group by passing the `groupSize`.
This function will return a LinkedHashMap already sorted in descending order of ocurrences of the group of words.

So for example, if you want to rank pairs of words in a text, you can call:

```java
LinkedHashMap<String, Integer> result = wordRankingUseCase.rankWordsFromTexts(2);
```
 