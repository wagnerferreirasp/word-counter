package org.example.wordcounter.app;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.example.wordcounter.app.files.FileTestUtils;
import org.example.wordcounter.core.text.Text;
import org.junit.jupiter.api.Test;

import static org.example.wordcounter.app.cli.options.Constants.GROUP_SIZE_OPTION;
import static org.example.wordcounter.app.cli.options.Constants.HELP_OPTION;
import static org.example.wordcounter.app.cli.options.Constants.INPUT_PATH_OPTION;
import static org.example.wordcounter.app.cli.options.Constants.LANGUAGE_OPTION;
import static org.example.wordcounter.app.cli.options.Constants.OUTPUT_PATH_OPTION;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTests {
	private static final String CONTENT_OF_COMPLEX_TXT = "complexcontent,4\nsimplecontent,1";
	private static final String OUTPUT_CSV = "output.csv";
	public static final String INPUT_FOLDER = "texts/utf8";

	@Test
	void noParams_shouldShowInvalidParameterMessage() {
		assertStderrContains(
			() ->  Main.main(new String[]{}),
			INPUT_PATH_OPTION, HELP_OPTION
		);
	}

	@Test
	void helpCommand_shouldShowOptions() {
		assertStderrContains(
			() ->  Main.main(new String[]{HELP_OPTION}),
			HELP_OPTION, INPUT_PATH_OPTION, OUTPUT_PATH_OPTION
		);
	}

	@Test
	void invalidParam_shouldShowInvalidParameterMessage() {
		String invalidParam = "--asdasdasd";
		assertStderrContains(
			() ->  Main.main(new String[]{invalidParam}),
			invalidParam
		);
	}

	@Test
	void executeWithOutputNotWritable_exceptionShouldBubbleUp() {
		File outputFile = new File(FileTestUtils.getFullPath(OUTPUT_CSV));
		boolean lockedWrite = outputFile.setWritable(false);

		assertTrue(lockedWrite);
		assertThrows(IOException.class,
			() -> Main.main(givenValidArgs())
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

	@Test
	void mainMethodWithValidArgs_shouldCreateFile() {
		Main.main(givenValidArgs());

		Text text = FileTestUtils.getText(OUTPUT_CSV, StandardCharsets.UTF_8);
		assertEquals(CONTENT_OF_COMPLEX_TXT, text.getContent());
		cleanupOutputFile();
	}

	private void cleanupOutputFile() {
		File file = new File(FileTestUtils.getFullPath(OUTPUT_CSV));
		file.deleteOnExit();
	}

	private String[] givenValidArgs() {
		return new String[]{
			GROUP_SIZE_OPTION, "1",
			LANGUAGE_OPTION, "en",
            INPUT_PATH_OPTION, FileTestUtils.getFullPath(INPUT_FOLDER),
			OUTPUT_PATH_OPTION, FileTestUtils.getFullPath(OUTPUT_CSV)
		};
	}

	private void assertStderrContains(Runnable test, String... expectedContents) {
		PrintStream originalErr = System.err;
		ByteArrayOutputStream errContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errContent));

		test.run();

		for (String expected : expectedContents) {
			assertThat(errContent.toString(), containsString(expected));
		}

		System.setErr(originalErr);
	}

}
