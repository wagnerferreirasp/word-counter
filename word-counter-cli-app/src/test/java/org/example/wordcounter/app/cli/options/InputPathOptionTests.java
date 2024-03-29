package org.example.wordcounter.app.cli.options;

import java.io.File;

import org.example.wordcounter.app.files.FileTestUtils;
import org.junit.jupiter.api.Test;

import static org.example.wordcounter.app.cli.options.Options.INPUT_PATH_OPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputPathOptionTests extends OptionsBaseTests {

	@Test
	void notPassingPath_ShouldThrowException() {
		String[] args = givenOptionsNotPassing(INPUT_PATH_OPTION);

		assertInvalidOptionIsThrown(args);
	}

	@Test
	void pathDoesntExist_ShouldThrowException() {
		String fullPath = FileTestUtils.getFullPath("nonexistent");
		String[] args = givenValidOptionsWith(INPUT_PATH_OPTION, fullPath);

		assertInvalidOptionIsThrown(args);
	}

	@Test
	void passSingleFile_ShouldParseCorrectly() {
		String fullPath = FileTestUtils.getFullPath("texts/utf8/simple.txt");
		String[] args = givenValidOptionsWith(INPUT_PATH_OPTION, fullPath);

		Options options = optionsParser.parse(args).orElseThrow();

		assertEquals(new File(fullPath), options.getInputPath());
	}

	@Test
	void passFolder_ShouldParseCorrectly() {
		String fullPath = FileTestUtils.getFullPath("texts/");
		String[] args = givenValidOptionsWith(INPUT_PATH_OPTION, fullPath);

		Options options = optionsParser.parse(args).orElseThrow();

		assertEquals(new File(fullPath), options.getInputPath());
	}
}
