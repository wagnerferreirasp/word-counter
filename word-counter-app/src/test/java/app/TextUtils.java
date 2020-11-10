package app;

import boundaries.Text;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TextUtils {
	public static Text givenSimpleTextFile() {
		return getText("texts/simple.txt", StandardCharsets.UTF_8);
	}

	public static Text givenEmptyText() {
		return TextUtils.getText("texts/empty.txt", StandardCharsets.UTF_8);
	}

	public static Text givenFileWithCp1250() {
		return getText("texts/cp1250.txt", Charset.forName("cp1250"));
	}

	public static Text givenComplexFile() {
		return getText("texts/complex.txt", StandardCharsets.UTF_8);
	}

	private static Text getText(String fileName, Charset encoding) {
		InputStream is = TextUtils.class.getClassLoader().getResourceAsStream(fileName);
		return new InputStreamText(is, encoding);
	}
}
