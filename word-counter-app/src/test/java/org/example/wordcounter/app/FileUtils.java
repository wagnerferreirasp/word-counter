package org.example.wordcounter.app;

import org.example.wordcounter.app.boundaries.InputStreamText;
import org.example.wordcounter.core.boundaries.Text;

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
