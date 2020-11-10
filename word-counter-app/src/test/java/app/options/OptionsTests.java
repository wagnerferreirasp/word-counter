package app.options;

import app.options.exception.InvalidOptionException;
import app.options.parser.OptionsParser;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OptionsTests {
	public static final String PATH_OPTION = "--path";
	public final String GROUP_SIZE_OPTION = "--group-size";
	public final String ALPHABET_OPTION = "--alphabet";

	public static final String PATH_VALID_VALUE = "validInput.txt";
	public static final Integer GROUP_SIZE_VALID_VALUE = 1;
	public static final String ALPHABET_VALID_VALUE = "\\p{Alpha}";

	@Test
	void notPassingAnyArgs_ShouldThrowException() {
		String[] args = {};

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

	protected void assertInvalidOptionIsThrown(String[] args) {
		assertThrows(InvalidOptionException.class, () -> {
			OptionsParser.parse(args);
		});
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
			put(ALPHABET_OPTION, ALPHABET_VALID_VALUE);
			put(PATH_OPTION, PATH_VALID_VALUE);
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
