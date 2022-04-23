package org.example.wordcounter.app.cli.options;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionsCommonTests extends OptionsBaseTests {
	@Test
	void notPassingAnyArgs_ShouldThrowException() {
		String[] args = {};

		assertInvalidOptionIsThrown(args);
	}

	@Test
	void passInvalidOption_ShouldThrowException() {
		String[] args = givenValidOptionsWith(INVALID_OPTION, INVALID_OPTION_VALUE);

		assertInvalidOptionIsThrown(args);
	}

	@Test
	void passValidRequiredOptions_ShouldParseCorrectly() {
		String[] args = getValidRequiredArgs().toArray(String[]::new);

		Options options = optionsParser.parse(args).orElseThrow();

		assertEquals(INPUT_PATH_VALID_VALUE, options.getInputPath().getName());
		assertEquals(OUTPUT_PATH_VALID_VALUE, options.getOutputPath().getName());
	}

}
