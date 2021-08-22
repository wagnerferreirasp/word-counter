package org.example.wordcounter.app;

import org.example.wordcounter.app.options.InvalidOptionException;
import org.example.wordcounter.app.options.Options;
import org.example.wordcounter.app.options.OptionsParser;

import java.util.LinkedHashMap;

public class AppRunner {

	public ExecutionResult run(String[] args) {
		try {
			return tryToRun(args);
		} catch (InvalidOptionException e) {
			return new ExecutionResult(String.format("%s\n%s",
					e.getCause().getMessage(),
					"Try using the option '--help'"
			));
		}
	}

	private ExecutionResult tryToRun(String[] args) {
		ApplicationConfig config = new ApplicationConfig(args);
		Options options = config.getOptions();
		OptionsParser optionsParser = ApplicationConfig.getOptionsParser(options);
		if (options.isHelp()) {
			return new ExecutionResult(optionsParser.getHelp());
		}
		LinkedHashMap<String, Integer> map = config.getUseCase().rankWordsFromTexts(options.getGroupSize());
		return new ExecutionResult(options, formatOutput(map), "Successfully ranked words!");
	}

	private static String formatOutput(LinkedHashMap<String, Integer> map) {
		StringBuilder sb = new StringBuilder();
		map.forEach((word, freq) -> sb.append(String.format("%s\t%d\n", word, freq)));
		return sb.toString();
	}
}
