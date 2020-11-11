package app.options;

import app.options.parsers.OptionsParser;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathOptionTests extends OptionsBaseTests {
	@Test
	void notPassingPath_ShouldThrowException() {
		String[] args = givenOptionsNotPassing(PATH_OPTION);

		assertInvalidOptionIsThrown(args);
	}

	@Test
	void passSingleFile_ShouldParseCorrectly() {
		String[] args = givenValidOptionsWith(PATH_OPTION, "texts/utf8/simple.txt");

		Options options = OptionsParser.parse(args);

		assertEquals(new File("texts/utf8/simple.txt"), options.path);
	}

	@Test
	void passFolder_ShouldParseCorrectly() {
		String[] args = givenValidOptionsWith(PATH_OPTION, "texts/");

		Options options = OptionsParser.parse(args);

		assertEquals(new File("texts/"), options.path);
	}
}
