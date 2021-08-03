package org.example.wordcounter.app;

import org.example.wordcounter.app.boundaries.InputStreamText;
import org.example.wordcounter.core.boundaries.Text;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.example.wordcounter.app.Constants.ALPHABET_OPTION;
import static org.example.wordcounter.app.Constants.ENGLISH_ALPHABET;
import static org.example.wordcounter.app.Constants.GROUP_SIZE_OPTION;
import static org.example.wordcounter.app.Constants.OUTPUT_OPTION;
import static org.example.wordcounter.app.Constants.PATH_OPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTests {
	private static final String CONTENT_OF_COMPLEX_TXT = "complexcontent\t4\nsimplecontent\t1";

	@Test
	void testMain() {
		String base64Alphabet = Base64.getEncoder().encodeToString(ENGLISH_ALPHABET.getBytes());
		String[] args = {
				GROUP_SIZE_OPTION, "1",
				ALPHABET_OPTION, base64Alphabet,
				PATH_OPTION, FileUtils.getResourcePath("texts/utf8"),
				OUTPUT_OPTION,  FileUtils.getResourcePath("") + "output"
		};

		Application.main(args);

		Text text = FileUtils.getText("output", StandardCharsets.UTF_8);
		assertEquals(CONTENT_OF_COMPLEX_TXT, text.getContent());
	}
}
