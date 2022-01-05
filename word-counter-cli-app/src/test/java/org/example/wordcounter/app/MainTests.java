package org.example.wordcounter.app;

import org.example.wordcounter.core.text.Text;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.example.wordcounter.app.cli.options.Constants.ALPHABET_OPTION;
import static org.example.wordcounter.app.cli.options.Constants.ENGLISH_ALPHABET;
import static org.example.wordcounter.app.cli.options.Constants.GROUP_SIZE_OPTION;
import static org.example.wordcounter.app.cli.options.Constants.HELP_OPTION;
import static org.example.wordcounter.app.cli.options.Constants.INPUT_PATH_OPTION;
import static org.example.wordcounter.app.cli.options.Constants.OUTPUT_PATH_OPTION;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTests {
	private static final String CONTENT_OF_COMPLEX_TXT = "complexcontent,4\nsimplecontent,1";
	private static final String OUTPUT_CSV = "output.csv";

	@Test
	void noParams_shouldShowInvalidParameterMessage() {
		assertExceptionThrownWithMessageContaining(
			() ->  Main.execute(new String[]{}),
			ALPHABET_OPTION, INPUT_PATH_OPTION, HELP_OPTION
		);
	}

	@Test
	void helpCommand() {
		assertExceptionThrownWithMessageContaining(
			() ->  Main.execute(new String[]{HELP_OPTION}),
			ALPHABET_OPTION, INPUT_PATH_OPTION, HELP_OPTION
		);
	}

	@Test
	void invalidParam_shouldShowInvalidParameterMessage() {
		String invalidParam = "--asdasdasd";
		assertExceptionThrownWithMessageContaining(
			() ->  Main.execute(new String[]{invalidParam}),
			invalidParam
		);
	}

	@Test
	void executeWithValidArgs_shouldCreateFile() {
		Main.execute(givenValidArgs());

		Text text = FileUtils.getText(OUTPUT_CSV, StandardCharsets.UTF_8);
		assertEquals(CONTENT_OF_COMPLEX_TXT, text.getContent());
	}

	@Test
	void mainMethodWithValidArgs_shouldCreateFile() {
		Main.main(givenValidArgs());

		Text text = FileUtils.getText(OUTPUT_CSV, StandardCharsets.UTF_8);
		assertEquals(CONTENT_OF_COMPLEX_TXT, text.getContent());
	}

	@Test
	void executeWithOutputNotWritable_shouldThrowException() {
		File outputFile = new File(FileUtils.getFullPath(OUTPUT_CSV));
		boolean lockedWrite = outputFile.setWritable(false);

		assertTrue(lockedWrite);
		assertThrows(UncheckedIOException.class,
			() -> Main.execute(givenValidArgs())
		);
		boolean unlockedWrite = outputFile.setWritable(true);
		assertTrue(unlockedWrite);
	}

	@Test
	void mainMethodWithInvalidParam_shouldShowToStderr() {
		String invalidParam = "--asdasdasd";

		assertStderrContains(
			() -> Main.main(new String[]{invalidParam}),
			invalidParam
		);
	}

	private String[] givenValidArgs() {
		String base64Alphabet = Base64.getEncoder().encodeToString(ENGLISH_ALPHABET.getBytes());
		return new String[]{
			GROUP_SIZE_OPTION, "1",
			ALPHABET_OPTION, base64Alphabet,
            INPUT_PATH_OPTION, FileUtils.getFullPath("texts/utf8"),
			OUTPUT_PATH_OPTION, FileUtils.getFullPath(OUTPUT_CSV)
		};
	}

	private void assertExceptionThrownWithMessageContaining(Runnable test, String... expectedContents) {
		ConfigurationException expected = assertThrows(
			ConfigurationException.class, test::run
		);

		assertMessageContains(expected.getMessage(), expectedContents);
	}

	private void assertMessageContains(String message, String... expectedContents) {
		for (String expected : expectedContents) {
			assertThat(message, containsString(expected));
		}
	}

	private void assertStderrContains(Runnable test, String expectedContent) {
		PrintStream originalErr = System.err;
		ByteArrayOutputStream errContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errContent));

		test.run();
		assertThat(errContent.toString(), containsString(expectedContent));

		System.setOut(originalErr);
	}

}
