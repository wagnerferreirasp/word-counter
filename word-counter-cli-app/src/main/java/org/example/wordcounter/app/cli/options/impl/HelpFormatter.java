package org.example.wordcounter.app.cli.options.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.example.wordcounter.app.cli.options.FormattedHelp;
import org.example.wordcounter.app.cli.options.Language;
import org.example.wordcounter.app.cli.options.Options;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterDescription;

class HelpFormatter {

	private final JCommander jCommander;
	private final String appName;

	public HelpFormatter(JCommander jCommander, String appName) {
		this.jCommander = jCommander;
		this.appName = appName;
	}

	public FormattedHelp getHelp() {
		List<ParameterDescription> params = new ArrayList<>(jCommander.getFields().values());
		return new FormattedHelp(
			createUsage(),
			createRequiredOptions(params),
			createNonRequiredOptions(params)
		);
	}

	private String createUsage() {
		return "\nUsage: java -jar " + appName + "-{version}.jar [options]";
	}

	private List<String> createRequiredOptions(List<ParameterDescription> params) {
		return params.stream()
			.filter(param -> param.getParameter().getParameter().required())
			.map(this::formatOption)
			.collect(Collectors.toList());
	}

	private List<String> createNonRequiredOptions(List<ParameterDescription> params) {
		return params.stream()
			.filter(param -> !param.getParameter().getParameter().required())
			.map(param -> formatOption(param) + formatDefault(param))
			.collect(Collectors.toList());
	}

	private String formatOption(ParameterDescription param) {
		return
			"\n\t\t" + param.getNames() +
			"\n\t\t" + formatMultilineDescription(param);
	}

	private String formatDefault(ParameterDescription param) {
		return String.format("\n\t\tDefault: %s", param.getDefault());
	}

	private String formatMultilineDescription(ParameterDescription param) {
		String description = param.getDescription();
		if (param.getNames().contains(Options.LANGUAGE_OPTION)) {
			description = String.format(description, formatAvailableLanguages());
		}
		return String.join("\n\t\t", description.split("\n"));
	}

	private String formatAvailableLanguages() {
		return Language.AVAILABLE_LANGUAGES.stream()
			.map(Language::toString)
			.collect(Collectors.joining(", "));
	}

}
