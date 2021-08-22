package org.example.wordcounter.app;

import org.example.wordcounter.app.boundaries.FileTextProvider;
import org.example.wordcounter.app.options.Options;
import org.example.wordcounter.app.options.jcommander.JCommanderOptionsParser;
import org.example.wordcounter.app.options.OptionsParser;
import org.example.wordcounter.core.boundaries.TextProvider;
import org.example.wordcounter.core.usecases.WordRankingUseCase;

public class ApplicationConfig {

	private final Options options;

	public ApplicationConfig(String[] argv) {
		this.options = getOptionsParser(new Options()).parse(argv);
	}

	public Options getOptions() {
		return options;
	}

	public static OptionsParser getOptionsParser(Options options) {
		return new JCommanderOptionsParser(options);
	}

	public TextProvider getTextProvider() {
		return new FileTextProvider(options.getPath(), options.getExclusions(), options.getEncoding());
	}

	public WordRankingUseCase getUseCase() {
		return new WordRankingUseCase(options.getAlphabet(), getTextProvider());
	}
}
