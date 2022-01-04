package org.example.wordcounter.app.files;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

public class CsvWriter {

    public static void writeRankingToCsv(LinkedHashMap<String, Integer> ranking, File outputPath, Charset encoding) {
        StringBuilder stringBuilder = new StringBuilder();
        ranking.forEach(writeCsvLine(stringBuilder));
        String content = stringBuilder.toString();
        sneakyWriteContent(outputPath, encoding, content);
    }

    private static BiConsumer<String, Integer> writeCsvLine(StringBuilder sb) {
        return (word, freq) ->
            sb.append(String.format("%s,%d\n", word, freq));
    }

    @SneakyThrows
    private static void sneakyWriteContent(File outputPath, Charset encoding, String content) {
        try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
            outputStream.write(content.getBytes(encoding));
        }
    }

}