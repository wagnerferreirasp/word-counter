package org.example.wordcounter.app;

import lombok.SneakyThrows;
import org.example.wordcounter.core.text.Text;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.example.wordcounter.app.cli.options.Constants.ALPHABET_OPTION;
import static org.example.wordcounter.app.cli.options.Constants.ENGLISH_ALPHABET;
import static org.example.wordcounter.app.cli.options.Constants.GROUP_SIZE_OPTION;
import static org.example.wordcounter.app.cli.options.Constants.HELP_OPTION;
import static org.example.wordcounter.app.cli.options.Constants.OUTPUT_PATH_OPTION;
import static org.example.wordcounter.app.cli.options.Constants.PATH_OPTION;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainTests {
	private static final String CONTENT_OF_COMPLEX_TXT = "complexcontent,4\nsimplecontent,1";

	@Test
	void noParams_shouldShowInvalidParameterMessage() {
		assertExceptionThrownWithMessageContaining(
			() ->  sneakyExecute(new String[]{}),
			ALPHABET_OPTION, PATH_OPTION, HELP_OPTION
		);
	}

	@Test
	void helpCommand() {
		assertExceptionThrownWithMessageContaining(
			() ->  sneakyExecute(new String[]{HELP_OPTION}),
			ALPHABET_OPTION, PATH_OPTION, HELP_OPTION
		);
	}

	@Test
	void invalidParam_shouldShowInvalidParameterMessage() {
		String invalidParam = "--asdasdasd";
		assertExceptionThrownWithMessageContaining(
			() ->  sneakyExecute(new String[]{invalidParam}),
			invalidParam
		);
	}

	@Test
	void mainMethodWithInvalidParam_shouldShow() {
		String invalidParam = "--asdasdasd";
		Main.main(new String[]{invalidParam});
	}

	@Test
	void executeWithValidArgs_shouldCreateFile() {
		sneakyExecute(givenValidArgs());

		Text text = FileUtils.getText("output", StandardCharsets.UTF_8);
		assertEquals(CONTENT_OF_COMPLEX_TXT, text.getContent());
	}

	@Test
	void mainMethodWithValidArgs_shouldCreateFile() {
		Main.main(givenValidArgs());

		Text text = FileUtils.getText("output", StandardCharsets.UTF_8);
		assertEquals(CONTENT_OF_COMPLEX_TXT, text.getContent());
	}

	private String[] givenValidArgs() {
		String base64Alphabet = Base64.getEncoder().encodeToString(ENGLISH_ALPHABET.getBytes());
		return new String[]{
				GROUP_SIZE_OPTION, "1",
				ALPHABET_OPTION, base64Alphabet,
				PATH_OPTION, FileUtils.getResourcePath("texts/utf8"),
				OUTPUT_PATH_OPTION,  FileUtils.getResourcePath("") + "output"
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

	@SneakyThrows
	private void sneakyExecute(String[] args) {
		Main.execute(args);
	}
}
