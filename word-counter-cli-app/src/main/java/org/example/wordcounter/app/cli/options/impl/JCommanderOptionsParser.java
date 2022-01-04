package org.example.wordcounter.app.cli.options.impl;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.example.wordcounter.app.cli.options.InvalidOptionException;
import org.example.wordcounter.app.cli.options.Options;
import org.example.wordcounter.app.cli.options.OptionsParser;

public class JCommanderOptionsParser implements OptionsParser {

	private static final String APP_NAME = "word-counter-cli-app";

	private final Options options;
	private final JCommander jCommander = JCommander.newBuilder().build();

	public JCommanderOptionsParser() {
		this.options = new Options();
		jCommander.addObject(options);
		jCommander.setProgramName(APP_NAME);
	}

	@Override
	public Options parse(String[] args) {
		try {
			jCommander.parse(args);
			return options;
		} catch (ParameterException e) {
			throw new InvalidOptionException(e);
		}
	}

	@Override
	public String getHelp() {
		StringBuilder sb = new StringBuilder();
		jCommander.getUsageFormatter().usage(sb);
		return sb.toString();
	}

}
