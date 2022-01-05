package org.example.wordcounter.app.cli.options;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.example.wordcounter.app.cli.options.Constants.INPUT_PATH_OPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathOptionTests extends OptionsBaseTests {
	@Test
	void notPassingPath_ShouldThrowException() {
		String[] args = givenOptionsNotPassing(INPUT_PATH_OPTION);

		assertInvalidOptionIsThrown(args);
	}

	@Test
	void passSingleFile_ShouldParseCorrectly() {
		String[] args = givenValidOptionsWith(INPUT_PATH_OPTION, "texts/utf8/simple.txt");

		Options options = optionsParser.parse(args);

		assertEquals(new File("texts/utf8/simple.txt"), options.getInputPath());
	}

	@Test
	void passFolder_ShouldParseCorrectly() {
		String[] args = givenValidOptionsWith(INPUT_PATH_OPTION, "texts/");

		Options options = optionsParser.parse(args);

		assertEquals(new File("texts/"), options.getInputPath());
	}
}
