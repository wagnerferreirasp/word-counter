package app;

import boundaries.Text;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputStreamTextTests {
	@Test
	void emptyFile_ShouldReturnEmptyContent() {
		Text text = TextUtils.givenEmptyText();
		String content = text.getContent();

		String expected = "";
		assertEquals(expected, content);
	}

	@Test
	void simpleFile_ShouldReturnItsContents() {
		Text text = TextUtils.givenSimpleTextFile();
		String content = text.getContent();

		String expected = "simpleContent";
		assertEquals(expected, content);
	}

	@Test
	void simpleFileWithCp1250Encoding_ShouldReturnItsContents() {
		Text text = TextUtils.givenFileWithCp1250();
		String content = text.getContent();

		String expected = "příští zaměstnanec";
		assertEquals(expected, content);
	}

	@Test
	void complexFileWithLineEndings_ShouldReturnItsContents() {
		Text text = TextUtils.givenComplexFile();
		String content = text.getContent();

		String expected = "complexContent\ncomplexContent\ncomplexContent\ncomplexContent";
		assertEquals(expected, content);
	}
}
