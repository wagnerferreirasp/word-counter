package org.example.wordcounter.app.options;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

public class OptionsParser {
	public static Options parse(String[] argv) {
		try {
			return tryToParseOptions(argv);
		} catch (ParameterException e) {
			throw new InvalidOptionException(e);
		}
	}

	private static Options tryToParseOptions(String[] argv) {
		Options options = new Options();
		JCommander jCommander = JCommander.newBuilder()
				.addObject(options)
				.programName("word-counter-app")
				.build();
		jCommander.parse(argv);
		if (options.isHelp()) {
			jCommander.usage();
		}
		return options;
	}
}
