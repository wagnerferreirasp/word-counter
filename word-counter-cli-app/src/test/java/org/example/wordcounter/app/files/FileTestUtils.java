package org.example.wordcounter.app.files;

import org.example.wordcounter.core.text.Text;

import java.io.File;
import java.nio.charset.Charset;

public class FileTestUtils {

	public static String getFullPath(String relativeName) {
		String projectRoot = System.getProperty("user.dir");
		return String.format("%s/src/test/resources/%s", projectRoot, relativeName);
	}

	public static Text getText(String fileName, Charset encoding) {
		File file = new File(getFullPath(fileName));
		return new FileText(file, encoding);
	}

}
