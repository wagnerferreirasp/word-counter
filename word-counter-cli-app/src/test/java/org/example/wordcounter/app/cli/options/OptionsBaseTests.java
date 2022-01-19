package org.example.wordcounter.app.cli.options;

import java.util.ArrayList;
import java.util.List;

import org.example.wordcounter.app.cli.options.impl.JCommanderOptionsParser;
import org.example.wordcounter.app.files.FileTestUtils;

import static org.example.wordcounter.app.cli.options.Options.INPUT_PATH_OPTION;
import static org.example.wordcounter.app.cli.options.Options.OUTPUT_PATH_OPTION;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OptionsBaseTests {
	public static final String OUTPUT_PATH_VALID_VALUE = "validOutput.txt";
	public static final String INPUT_PATH_VALID_VALUE = "validInput.txt";
	public static final String ALPHABET_VALID_VALUE = "\\p{Alpha}";
	public static final String BASE64_ALPHABET_VALID_VALUE = "XHB7QWxwaGF9";
	public static final String INVALID_OPTION = "--invalid-option";
	public static final String INVALID_OPTION_VALUE = "invalid-option-value";

	OptionsParser optionsParser = new JCommanderOptionsParser();

	protected void assertInvalidOptionIsThrown(String[] args) {
		assertThrows(InvalidOptionException.class, () -> optionsParser.parse(args));
	}

	protected String[] givenOptionsNotPassing(String option) {
		List<String> validArgs = getValidRequiredArgs();
		int index = validArgs.indexOf(option);
		if (index != -1) {
			validArgs.remove(index);
			validArgs.remove(index+1);
		}
		return validArgs.toArray(String[]::new);
	}

	protected String[] givenValidOptionsWith(String option, String value) {
		List<String> validArgs = getValidRequiredArgs();
		int index = validArgs.indexOf(option);
		if (index != -1) {
			validArgs.set(index + 1, value);
		} else {
			validArgs.add(option);
			validArgs.add(value);
		}
		return validArgs.toArray(String[]::new);
	}

	protected List<String> getValidRequiredArgs() {
		return new ArrayList<>(List.of(
			INPUT_PATH_OPTION, FileTestUtils.getFullPath(INPUT_PATH_VALID_VALUE),
			OUTPUT_PATH_OPTION, FileTestUtils.getFullPath(OUTPUT_PATH_VALID_VALUE)
		));
	}

}
