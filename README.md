# Word Counter

This is a project that helps you count the occurrences of words in texts and rank them.
It's written in Java 11, with TDD and Clean Architecture (also known as Hexagonal Architecture).

## Modules

The project is separated into two modules, `word-counter-core` and `word-counter-cli-app`

### word-counter-core 

This is a very simple module, responsible for the core functionality of the project: 
counting and ranking words in a list of texts. A text here is defined as the interface  
```java
interface Text {
    String getContent();
}
```
Where you can implement to get the content as a String that will be searched to count the words.
To provide the list of texts, you need to implement the following:
```java
interface TextProvider {
    List<Text> findAll();
}
```

With those two implementations, the use case WordRankingService can be called, for example:

```java
public class Main {
    public static void main(String[] args) {
        TextProvider myTextProvider = new MyTextProvider(); // My implementation of TextProvider
        String greekAlphabet = "ΑαΒβΓγΔδΕεΖζΗηΘθΙιΚκΛλΜμΝνΞξΟοΠπΡρΣσςΤτΥυΦφΧχΨψΩω"; // The list of chars to be considered as part of a word
        int groupSize = 2; // I want to count pairs of words together and find the most common pairs
        WordRankingService rankingService = new WordRankingService(myAlphabet, myTextProvider);
        LinkedHashMap<String, Integer> ranking = rankingService.rankWordsFromTexts(groupSize);
        
        /* 
         * Here ranking is an ordered map with the ranking of words, 
         * where the key is the word (or group of words) and the value is the number of occurrences
         */  
    }
}
```

### word-counter-cli-app

This is a cli application to rank words from files in a directory. 
It will search all files inside the directory recursively and will output a csv file with the ranking 
of the most common words (or group of words). It uses the module `word-counter-core` and implements the boundaries defined by it, 
such as Text and TextProvider to read from files. Check below how to build and run the application.

## How to build

Run in your terminal from the root directory:

```shell
# To build the core module
./gradlew word-counter-core:build

# To build the cli app module
./gradlew word-counter-cli-app:build

# To build both modules
./gradlew build
```

## How to run the cli app

To run the `word-counter-cli-app`, call in your terminal:

```shell
java -jar word-counter-cli-app-1.0-SNAPSHOT.jar [options]
```
with the possible options:
```
Required options:
    --output-path, -o
    Output file path - the csv location to the results

    --input-path, -i
    Input Path - file or directory to count the words. In case of a directory, it will consider all files recursively

Non-required options:
    --help, -h
    Shows Help - shows how to use the application with its options
    Default: false

    --encoding, -e
    Encoding - encoding to consider when reading files from --input-path
    Default: UTF-8

    --group-size, -g
    Group size - number of words to group and count together
    Default: 1

    --language, -l
    Language - the language of the texts.
    Possible values: EN (English), CZ (Czech), ES (Spanish), PT (Portuguese)
    or a custom language by passing the list of letters encoded in base64 - CUSTOM:<base64alphabet>
    Default: EN (English)
```

Examples:

```shell
# Rank spanish words from files with encoding cp1252 inside spanish-texts/ and output it to spanish-words-ranking.csv
java -jar word-counter-cli-app-1.0-SNAPSHOT.jar -e cp1252 -l ES -i spanish-texts/ -o spanish-words-ranking.csv  

# Rank pairs of english words from files inside english-texts/ and output it to english-pairs.csv 
java -jar word-counter-cli-app-1.0-SNAPSHOT.jar -i english-texts/ -o english-pairs.csv -g 2

# Rank greek words from files inside greek-texts/ and output it to greek-words-ranking.csv, where a custom language (greek) is being passed encoded in base64 (encoded from ΑαΒβΓγΔδΕεΖζΗηΘθΙιΚκΛλΜμΝνΞξΟοΠπΡρΣσςΤτΥυΦφΧχΨψΩω)
java -jar word-counter-cli-app-1.0-SNAPSHOT.jar -i greek-texts/ -o greek-words-ranking.csv -l CUSTOM:zpHOsc6SzrLOk86zzpTOtM6VzrXOls62zpfOt86YzrjOmc65zprOus6bzrvOnM68zp3Ovc6ezr7On86/zqDPgM6hz4HOo8+Dz4LOpM+EzqXPhc6mz4bOp8+HzqjPiM6pz4k=
```