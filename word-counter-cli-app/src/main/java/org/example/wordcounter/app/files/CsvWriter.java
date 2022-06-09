package org.example.wordcounter.app.files;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvWriter {

    int MINIMUM_OCCURRENCES = 1;

    /**
     * Writes the ranking to the outputPath
     * @param ranking the map with the ranking of words
     * @param outputPath the output path to save the csv file
     * @param encoding the encoding to save the csv file
     */
    public void writeRankingToCsv(LinkedHashMap<String, Integer> ranking, File outputPath, Charset encoding) {
        String content = ranking.entrySet().stream()
            .filter(entry -> entry.getValue() >= MINIMUM_OCCURRENCES)
            .map(this::toLine)
            .collect(Collectors.joining("\n"));
        writeToCsv(outputPath, encoding, content);
    }

    private String toLine(Map.Entry<String, Integer> rankingEntry) {
        String word = rankingEntry.getKey();
        Integer rank = rankingEntry.getValue();
        return String.format("%s,%d", word, rank);
    }

    @SneakyThrows
    private static void writeToCsv(File outputPath, Charset encoding, String content) {
        try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
            outputStream.write(content.getBytes(encoding));
        }
    }

}
