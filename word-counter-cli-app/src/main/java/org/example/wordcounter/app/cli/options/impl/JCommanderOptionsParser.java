package org.example.wordcounter.app.cli.options.impl;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterDescription;
import com.beust.jcommander.ParameterException;
import org.example.wordcounter.app.cli.options.InvalidOptionException;
import org.example.wordcounter.app.cli.options.Options;
import org.example.wordcounter.app.cli.options.OptionsParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JCommanderOptionsParser implements OptionsParser {

	private static final String APP_NAME = "word-counter-cli-app";

	private final Options options;
	private final JCommander jCommander = JCommander.newBuilder().build();

	public JCommanderOptionsParser() {
		this.options = new Options();
		jCommander.addObject(options);
		jCommander.setProgramName(APP_NAME);
		if (jCommander.getDescriptions() == null) {
			jCommander.createDescriptions();
		}
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
		List<ParameterDescription> params = new ArrayList<>(jCommander.getFields().values());
		return String.format(
			"\nUsage: java -jar %s-{version}.jar [options]" +
			"\n\tRequired options:" +
			"%s" +
			"\n\n\tNon-required options:" +
			"%s",
			APP_NAME, formatOptions(params, true), formatOptions(params, false));
	}

	private String formatOptions(List<ParameterDescription> params, boolean isRequired) {
		return params.stream()
			.filter(param -> isRequired == param.getParameter().getParameter().required())
			.map(this::formatOption)
			.collect(Collectors.joining("\n"));
	}

	private String formatOption(ParameterDescription param) {
		boolean isRequired = param.getParameter().getParameter().required();
		return String.format(
			"\n\t\t%s" +
			"\n\t\t%s" +
			"%s",
			param.getNames(),
			String.join("\n\t\t",
				param.getDescription().split("\n") // consider multiline descriptions
			),
			isRequired ? "" : String.format("\n\t\tDefault: %s", param.getDefault())
		);
	}

}
