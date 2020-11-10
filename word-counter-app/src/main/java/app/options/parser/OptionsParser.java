package app.options.parser;

import app.options.Options;
import app.options.exception.InvalidOptionException;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.jetbrains.annotations.NotNull;

public class OptionsParser {
	public static Options parse(String[] argv) {
		try {
			return tryToParseOptions(argv);
		} catch (ParameterException e) {
			throw new InvalidOptionException(e);
		}
	}

	@NotNull
	private static Options tryToParseOptions(String[] argv) {
		Options options = new Options();
		JCommander.newBuilder()
				.addObject(options)
				.build()
				.parse(argv);
		return options;
	}
}
