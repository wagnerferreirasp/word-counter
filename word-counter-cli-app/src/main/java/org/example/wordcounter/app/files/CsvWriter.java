package org.example.wordcounter.app.files;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility class to write the ranking of words to a csv file
 */
public final class CsvWriter {

    private CsvWriter() {
        // Utility class
    }

    /**
     * Writes the ranking to the outputPath
     * @param ranking the map with the ranking of words
     * @param outputPath the output path to save the csv file
     * @param encoding the encoding to save the csv file
     */
    public static void writeRankingToCsv(LinkedHashMap<String, Integer> ranking, File outputPath, Charset encoding) {
        String content = ranking.entrySet().stream()
            .map(CsvWriter::toLine)
            .collect(Collectors.joining("\n"));
        writeToCsv(outputPath, encoding, content);
    }

    private static String toLine(Map.Entry<String, Integer> rankingEntry) {
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
