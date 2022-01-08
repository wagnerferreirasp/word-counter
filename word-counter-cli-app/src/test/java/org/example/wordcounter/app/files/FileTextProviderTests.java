package org.example.wordcounter.app.files;

import org.example.wordcounter.core.text.Text;
import org.example.wordcounter.core.text.TextProvider;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.example.wordcounter.app.files.FileTextTests.CONTENT_OF_COMPLEX_TXT;
import static org.example.wordcounter.app.files.FileTextTests.CONTENT_OF_EMPTY_TXT;
import static org.example.wordcounter.app.files.FileTextTests.CONTENT_OF_FILEABC;
import static org.example.wordcounter.app.files.FileTextTests.CONTENT_OF_FILEBCD;
import static org.example.wordcounter.app.files.FileTextTests.CONTENT_OF_ROOTFILE;
import static org.example.wordcounter.app.files.FileTextTests.CONTENT_OF_SIMPLE_TXT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileTextProviderTests {

	@Test
	void fileDoesNotExist_ShouldThrowException() {
		assertThrows(NoSuchFileException.class,
			() -> new FileTextProvider(givenFileThatDoesntExist(), StandardCharsets.UTF_8)
		);
	}

	@Test
	void emptyFolder_ShouldReturnEmptyList() {
		TextProvider textProvider = new FileTextProvider(givenEmptyFolder(), StandardCharsets.UTF_8);

		List<Text> texts = textProvider.findAll();

		assertThat(texts, is(empty()));
	}

	@Test
	void simpleFile_ShouldLoadCorrectly() {
		TextProvider textProvider = new FileTextProvider(givenSimpleFile(), StandardCharsets.UTF_8);

		List<Text> all = textProvider.findAll();

		assertEquals(1, all.size());
		assertEquals(CONTENT_OF_SIMPLE_TXT, all.get(0).getContent());
	}

	@Test
	void simpleFolder_ShouldItsFiles() {
		File folder = givenSimpleFolder();
		Set<String> expectedContents = Set.of(
			CONTENT_OF_COMPLEX_TXT, CONTENT_OF_EMPTY_TXT, CONTENT_OF_SIMPLE_TXT
		);

		assertFindAllOfFolderReturnsContents(folder, expectedContents);
	}

	@Test
	void multiLevelFolder_ShouldLoadAllLevels() {
		File folder = givenMultiLevelFolder();
		Set<String> expectedContents = Set.of(
			CONTENT_OF_FILEABC, CONTENT_OF_ROOTFILE, CONTENT_OF_FILEBCD
		);

		assertFindAllOfFolderReturnsContents(folder, expectedContents);
	}

	private void assertFindAllOfFolderReturnsContents(File folder, Set<String> expectedContents) {
		TextProvider textProvider = new FileTextProvider(folder, StandardCharsets.UTF_8);

		Set<String> actualContents = textProvider.findAll()
			.stream()
			.map(Text::getContent)
			.collect(Collectors.toSet());

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

	private File givenEmptyFolder() {
		return givenPath("texts/emptyFolder");
	}

	private File givenMultiLevelFolder() {
		return givenPath("texts/multiLevel");
	}

	private File givenPath(String name) {
		return new File(FileTestUtils.getFullPath(name));
	}
}
