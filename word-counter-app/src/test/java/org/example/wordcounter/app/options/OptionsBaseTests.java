package org.example.wordcounter.app.options;

import org.example.wordcounter.app.Application;
import org.example.wordcounter.app.ApplicationConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.example.wordcounter.app.Constants.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OptionsBaseTests {
	private static final String OUTPUT_VALID_VALUE = "validOutput.txt";
	public static final String PATH_VALID_VALUE = "validInput.txt";
	public static final Integer GROUP_SIZE_VALID_VALUE = 1;
	public static final String ALPHABET_VALID_VALUE = "\\p{Alpha}";
	public static final String BASE64_ALPHABET_VALID_VALUE = "XHB7QWxwaGF9";
	public static final String INVALID_OPTION = "--invalid-option";
	public static final String INVALID_OPTION_VALUE = "invalid-option-value";

	protected OptionsParser optionsParser = ApplicationConfig.getOptionsParser(new Options());

	protected void assertInvalidOptionIsThrown(String[] args) {
		assertThrows(InvalidOptionException.class, () -> optionsParser.parse(args));
	}

	protected String[] givenValidOptionsWith(String option, String value) {
		Map<String, String> validArgs = getValidOpts();
		validArgs.put(option, value);
		return fromMap(validArgs);
	}

	protected String[] givenOptionsNotPassing(String option) {
		Map<String, String> validArgs = getValidOpts();
		validArgs.remove(option);
		return fromMap(validArgs);
	}

	protected Map<String, String> getValidOpts() {
		return new HashMap<String, String>(){{
			put(GROUP_SIZE_OPTION, GROUP_SIZE_VALID_VALUE.toString());
			put(ALPHABET_OPTION, BASE64_ALPHABET_VALID_VALUE);
			put(PATH_OPTION, PATH_VALID_VALUE);
			put(OUTPUT_OPTION, OUTPUT_VALID_VALUE);
		}};
	}

	protected String[] fromMap(Map<String, String> map) {
		return map.entrySet()
				.stream()
				.map((entry -> new String[]{entry.getKey(), entry.getValue()}))
				.flatMap(Stream::of)
				.toArray(String[]::new);
	}
}
