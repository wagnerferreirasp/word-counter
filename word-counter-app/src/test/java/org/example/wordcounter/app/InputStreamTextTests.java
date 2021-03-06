package org.example.wordcounter.app;

import org.example.wordcounter.app.boundaries.InputStreamText;
import org.example.wordcounter.core.boundaries.Text;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputStreamTextTests {

	public static final String CONTENT_OF_SIMPLE_TXT = "simpleContent";
	public static final String CONTENT_OF_CP1250_TXT = "příští zaměstnanec";
	public static final String CONTENT_OF_COMPLEX_TXT = "complexContent\ncomplexContent\ncomplexContent\ncomplexContent";
	public static final String CONTENT_OF_EMPTY_TXT = "";

	@Test
	void emptyFile_ShouldReturnEmptyContent() {
		Text text = givenEmptyText();
		String content = text.getContent();

		assertEquals(CONTENT_OF_EMPTY_TXT, content);
	}

	@Test
	void simpleFile_ShouldReturnItsContents() {
		Text text = givenSimpleTextFile();
		String content = text.getContent();

		assertEquals(CONTENT_OF_SIMPLE_TXT, content);
	}

	@Test
	void simpleFileWithCp1250Encoding_ShouldReturnItsContents() {
		Text text = givenFileWithCp1250();
		String content = text.getContent();

		assertEquals(CONTENT_OF_CP1250_TXT, content);
	}

	@Test
	void complexFileWithLineEndings_ShouldReturnItsContents() {
		Text text = givenComplexFile();
		String content = text.getContent();

		assertEquals(CONTENT_OF_COMPLEX_TXT, content);
	}

	public Text givenSimpleTextFile() {
		return getText("texts/utf8/simple.txt", StandardCharsets.UTF_8);
	}

	public Text givenEmptyText() {
		return getText("texts/utf8/empty.txt", StandardCharsets.UTF_8);
	}

	public Text givenFileWithCp1250() {
		return getText("texts/cp1250.txt", Charset.forName("cp1250"));
	}

	public Text givenComplexFile() {
		return getText("texts/utf8/complex.txt", StandardCharsets.UTF_8);
	}

	private Text getText(String fileName, Charset encoding) {
		InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
		return new InputStreamText(is, encoding);
	}
}
