package app.options;

import app.options.parsers.OptionsParser;
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
	void passValidOptions_ShouldParseCorrectly() {
		String[] args = fromMap(getValidOpts());

		Options options = OptionsParser.parse(args);

		assertEquals(GROUP_SIZE_VALID_VALUE, options.groupSize);
		assertEquals(ALPHABET_VALID_VALUE, options.alphabet);
		assertEquals(PATH_VALID_VALUE, options.path.getName());
	}
}
