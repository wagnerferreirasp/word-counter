package org.example.wordcounter.app.cli.options;

import java.util.Optional;

/**
 * The parser for the cli options.
 * It can be implemented using your favorite utility library, for example.
 */
public interface OptionsParser {
	/**
	 * Parses the args into Options object
	 * @param args the program args
	 * @throws InvalidOptionException in case of an invalid option passed
	 * @return the parsed Options object - Optional.empty for the help command
	 */
	Optional<Options> parse(String[] args);

	/**
	 * Provides the help output for the cli tool with the
	 * supported options
	 * @return the contents of the help output
	 */
	FormattedHelp getHelp();
}
