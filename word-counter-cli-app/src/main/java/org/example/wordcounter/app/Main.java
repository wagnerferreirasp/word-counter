package org.example.wordcounter.app;

import org.example.wordcounter.app.cli.options.Options;
import org.example.wordcounter.app.config.ApplicationConfig;
import org.example.wordcounter.app.files.CsvWriter;
import org.example.wordcounter.core.counter.WordRankingService;

import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) {
        try {
            Main.execute(args);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void execute(String[] args) {
        ApplicationConfig config = new ApplicationConfig(args);
        Options options = config.getOptions();
        WordRankingService useCase = config.getUseCase();
        LinkedHashMap<String, Integer> result = useCase.rankWordsFromTexts(options.getGroupSize());
        CsvWriter.writeRankingToCsv(result, options.getOutputPath(), options.getEncoding());
    }

}