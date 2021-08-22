package org.example.wordcounter.app;

import org.example.wordcounter.core.boundaries.Text;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import static org.example.wordcounter.app.Constants.ALPHABET_OPTION;
import static org.example.wordcounter.app.Constants.ENGLISH_ALPHABET;
import static org.example.wordcounter.app.Constants.GROUP_SIZE_OPTION;
import static org.example.wordcounter.app.Constants.HELP_OPTION;
import static org.example.wordcounter.app.Constants.OUTPUT_OPTION;
import static org.example.wordcounter.app.Constants.PATH_OPTION;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTests {
	private static final String CONTENT_OF_COMPLEX_TXT = "complexcontent\t4\nsimplecontent\t1";

	private final AppRunner appRunner = new AppRunner();

	@Test
	void noParams_shouldShowInvalidParameterMessage() {
		expectErrorMsgContains(appRunner.run(new String[]{}),
				ALPHABET_OPTION, PATH_OPTION, HELP_OPTION
		);
	}

	@Test
	void helpCommand() {
		ExecutionResult run = appRunner.run(new String[]{HELP_OPTION});
		expectErrorMsgContains(run, ALPHABET_OPTION, PATH_OPTION, HELP_OPTION);
	}

	@Test
	void invalidParam_shouldShowInvalidParameterMessage() {
		String invalidParam = "--asdasdasd";
		ExecutionResult run = appRunner.run(new String[]{invalidParam});
		expectErrorMsgContains(run, invalidParam);
	}

	@Test
	public void runWithValidArgs_shouldHaveEverythingRight() {
		String[] args = givenValidArgs();

		ExecutionResult run = appRunner.run(args);
		Optional<String> output = run.getOutput();
		assertHasValue(output, CONTENT_OF_COMPLEX_TXT);
		assertFalse(run.getErrorMsg().isPresent());
	}

	@Test
	void mainWithValidArgs_shouldCreateFile() {
		String[] args = givenValidArgs();

		Application.main(args);

		Text text = FileUtils.getText("output", StandardCharsets.UTF_8);
		assertEquals(CONTENT_OF_COMPLEX_TXT, text.getContent());
	}

	private void assertHasValue(Optional<String> output, String expected) {
		assertTrue(output.isPresent());
		assertThat(output.get(), containsString(expected));
	}

	private String[] givenValidArgs() {
		String base64Alphabet = Base64.getEncoder().encodeToString(ENGLISH_ALPHABET.getBytes());
		return new String[]{
				GROUP_SIZE_OPTION, "1",
				ALPHABET_OPTION, base64Alphabet,
				PATH_OPTION, FileUtils.getResourcePath("texts/utf8"),
				OUTPUT_OPTION,  FileUtils.getResourcePath("") + "output"
		};
	}

	private void expectErrorMsgContains(ExecutionResult run, String ...expectedContents) {
		for (String item : expectedContents) {
			assertHasValue(run.getErrorMsg(), item);
		}
	}
}
