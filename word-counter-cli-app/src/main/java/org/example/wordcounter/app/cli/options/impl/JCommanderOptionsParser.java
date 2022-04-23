package org.example.wordcounter.app.cli.options.impl;

import org.example.wordcounter.app.cli.options.FormattedHelp;
import org.example.wordcounter.app.cli.options.InvalidOptionException;
import org.example.wordcounter.app.cli.options.Options;
import org.example.wordcounter.app.cli.options.OptionsParser;

import java.util.Optional;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

public class JCommanderOptionsParser implements OptionsParser {

	private static final String APP_NAME = "word-counter-cli-app";

	private final JCommanderOptions jCommanderOptions;
	private final JCommander jCommander = JCommander.newBuilder().build();

	public JCommanderOptionsParser() {
		this.jCommanderOptions = new JCommanderOptions();
		jCommander.addObject(jCommanderOptions);
		jCommander.setProgramName(APP_NAME);
		if (jCommander.getDescriptions() == null) {
			jCommander.createDescriptions();
		}
	}

	@Override
	public Optional<Options> parse(String[] args) {
		try {
			jCommander.parse(args);
			return jCommanderOptions.toOptions();
		} catch (ParameterException e) {
			throw new InvalidOptionException(e);
		}
	}

	@Override
	public FormattedHelp getHelp() {
		return new HelpFormatter(jCommander, APP_NAME).getHelp();
	}

}
