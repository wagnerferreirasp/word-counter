package org.example.wordcounter.app;

import org.example.wordcounter.app.files.FileTextProvider;
import org.example.wordcounter.app.files.PathNotFoundException;
import org.example.wordcounter.core.text.Text;
import org.example.wordcounter.core.text.TextProvider;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.example.wordcounter.app.InputStreamTextTests.CONTENT_OF_COMPLEX_TXT;
import static org.example.wordcounter.app.InputStreamTextTests.CONTENT_OF_EMPTY_TXT;
import static org.example.wordcounter.app.InputStreamTextTests.CONTENT_OF_SIMPLE_TXT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileTextProviderTests {

	@Test
	void fileDoesNotExist_ShouldThrowException() {
		TextProvider textProvider = new FileTextProvider(givenFileThatDoesntExist(), StandardCharsets.UTF_8);

		assertThrows(PathNotFoundException.class, textProvider::findAll);
	}

	@Test
	void simpleFile_ShouldLoadCorrectly() {
		TextProvider textProvider = new FileTextProvider(givenSimpleFile(), StandardCharsets.UTF_8);

		List<Text> all = textProvider.findAll();

		assertEquals(1, all.size());
		assertEquals(CONTENT_OF_SIMPLE_TXT, all.get(0).getContent());
	}

	@Test
	void simpleFolder_ShouldLoadCorrectly() {
		TextProvider textProvider = new FileTextProvider(givenSimpleFolder(), StandardCharsets.UTF_8);

		Set<String> actualContents = textProvider.findAll()
				.stream()
				.map(Text::getContent)
				.collect(Collectors.toSet());

		Set<String> expectedContents = new HashSet<>(
			Arrays.asList(CONTENT_OF_SIMPLE_TXT, CONTENT_OF_EMPTY_TXT, CONTENT_OF_COMPLEX_TXT)
		);

		assertEquals(3, actualContents.size());
		assertEquals(expectedContents, actualContents);
	}

	private File givenFileThatDoesntExist() {
		return new File("texts/DOES_NOT_EXIST.txt");
	}

	private File givenSimpleFile() {
		return givenPath("texts/utf8/simple.txt");
	}

	private File givenSimpleFolder() {
		return givenPath("texts/utf8");
	}

	private File givenPath(String name) {
		return new File(FileUtils.getResourcePath(name));
	}
}
