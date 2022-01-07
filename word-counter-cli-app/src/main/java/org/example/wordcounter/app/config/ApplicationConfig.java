package org.example.wordcounter.app.config;

import lombok.Getter;
import org.example.wordcounter.app.cli.options.InvalidOptionException;
import org.example.wordcounter.app.cli.options.Options;
import org.example.wordcounter.app.cli.options.OptionsParser;
import org.example.wordcounter.app.cli.options.impl.JCommanderOptionsParser;
import org.example.wordcounter.app.files.FileTextProvider;
import org.example.wordcounter.core.counter.WordRankingService;
import org.example.wordcounter.core.text.TextProvider;

public class ApplicationConfig {

	@Getter
	private final Options options;

	public ApplicationConfig(String[] args) {
		OptionsParser optionsParser = getOptionsParser();
		try {
			this.options = optionsParser.parse(args);
			if (options.isHelp()) {
				throw new ConfigurationException(optionsParser.getHelp());
			}
		} catch (InvalidOptionException e) {
			String message = String.format("%s\n%s",
				e.getCause().getMessage(),
				optionsParser.getHelp()
			);
			throw new ConfigurationException(message);
		}
	}

	public OptionsParser getOptionsParser() {
		return new JCommanderOptionsParser();
	}

	public TextProvider getTextProvider() {
		return new FileTextProvider(options.getInputPath(), options.getEncoding());
	}

	public WordRankingService getUseCase() {
		return new WordRankingService(options.getLanguage().getAlphabetRegex(), getTextProvider());
	}
}
