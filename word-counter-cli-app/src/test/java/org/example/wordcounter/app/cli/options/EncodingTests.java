package org.example.wordcounter.app.cli.options;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import static org.example.wordcounter.app.cli.options.Options.Names.ENCODING_OPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EncodingTests extends OptionsBaseTests {

	@Test
	void dontPassEncoding_UTF8ShouldBeUsed() {
		String[] args = givenOptionsNotPassing(ENCODING_OPTION);
		Options options = optionsParser.parse(args);

		assertEquals(StandardCharsets.UTF_8, options.getEncoding());
	}

	@Test
	void passWrongEncoding_ShouldThrowException() {
		String[] args = givenValidOptionsWith(ENCODING_OPTION, "inexistentEncoding");

		assertInvalidOptionIsThrown(args);
	}

	@Test
	void passUTF16_ShouldParseCorrectly() {
		String[] args = givenValidOptionsWith(ENCODING_OPTION, "utf-16");

		Options options = optionsParser.parse(args);

		assertEquals(StandardCharsets.UTF_16, options.getEncoding());
	}

	@Test
	void passCp1250_ShouldParseCorrectly() {
		String[] args = givenValidOptionsWith(ENCODING_OPTION, "cp1250");

		Options options = optionsParser.parse(args);

		assertEquals(Charset.forName("cp1250"), options.getEncoding());
	}

}
