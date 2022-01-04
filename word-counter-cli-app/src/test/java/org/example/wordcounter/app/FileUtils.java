package org.example.wordcounter.app;

import org.example.wordcounter.app.files.InputStreamText;
import org.example.wordcounter.core.text.Text;

import java.io.InputStream;
import java.nio.charset.Charset;

public class FileUtils {
	public static String getResourcePath(String fileName) {
		return FileUtils.class.getClassLoader().getResource(fileName).getPath();
	}

	public static Text getText(String fileName, Charset encoding) {
		InputStream is = FileUtils.class.getClassLoader().getResourceAsStream(fileName);
		return new InputStreamText(is, encoding);
	}
}
