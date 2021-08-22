package org.example.wordcounter.app.options.jcommander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.example.wordcounter.app.Constants;
import org.example.wordcounter.app.options.InvalidOptionException;
import org.example.wordcounter.app.options.Options;
import org.example.wordcounter.app.options.OptionsParser;

public class JCommanderOptionsParser implements OptionsParser {

	private final Options options;
	private final JCommander jCommander = JCommander.newBuilder().build();

	public JCommanderOptionsParser(Options options) {
		this.options = options;
		jCommander.addObject(options);
	}

	@Override
	public Options parse(String[] argv) {
		try {
			jCommander.setProgramName(Constants.APP_NAME);
			jCommander.parse(argv);
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
