package org.example.wordcounter.app;

import org.example.wordcounter.app.boundaries.FileTextProvider;
import org.example.wordcounter.app.options.Options;
import org.example.wordcounter.app.options.OptionsParser;
import org.example.wordcounter.core.usecases.WordRankingUseCase;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;

public class Application {
	public static void main(String[] args) {
		Options options = OptionsParser.parse(args);
		if (options.isHelp()) {
			return;
		}

		FileTextProvider textProvider = new FileTextProvider(options.getPath(), options.getEncoding());
		WordRankingUseCase useCase = new WordRankingUseCase(options.getAlphabet(), textProvider);
		LinkedHashMap<String, Integer> map = useCase.rankWordsFromTexts(options.getGroupSize());
		writeToOutputFile(map, options);
	}

	private static void writeToOutputFile(LinkedHashMap<String, Integer> map, Options options) {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(options.getOutput(), false), options.getEncoding()))) {
			map.forEach((word, freq) -> {
				try {
					bw.write(String.format("%s\t%d\n", word, freq));
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}