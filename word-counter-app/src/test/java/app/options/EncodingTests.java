package app.options;

import app.options.parsers.OptionsParser;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncodingTests extends OptionsBaseTests {

	@Test
	void dontPassEncoding_UTF8ShouldBeUsed() {
		String[] args = givenOptionsNotPassing(ENCODING_OPTION);
		Options options = OptionsParser.parse(args);

		assertEquals(StandardCharsets.UTF_8, options.encoding);
	}

	@Test
	void passWrongEncoding_ShouldThrowException() {
		String[] args = givenValidOptionsWith(ENCODING_OPTION, "inexistentEncoding");

		assertInvalidOptionIsThrown(args);
	}

	@Test
	void passUTF16_ShouldParseCorrectly() {
		String[] args = givenValidOptionsWith(ENCODING_OPTION, "utf-16");

		Options options = OptionsParser.parse(args);

		assertEquals(StandardCharsets.UTF_16, options.encoding);
	}

	@Test
	void passCp1250_ShouldParseCorrectly() {
		String[] args = givenValidOptionsWith(ENCODING_OPTION, "cp1250");

		Options options = OptionsParser.parse(args);

		assertEquals(Charset.forName("cp1250"), options.encoding);
	}
}
