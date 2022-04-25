package org.example.wordcounter.app.files;

import lombok.SneakyThrows;
import org.example.wordcounter.core.text.Text;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Implements a Text using a File
 */
public class FileText implements Text {
	private final File file;
	private final Charset encoding;

	public FileText(File path, Charset encoding) {
		if (path.isDirectory()) {
			throw new IllegalArgumentException("Path can't be a directory");
		}
		this.file = path;
		this.encoding = encoding;
	}

	@Override
	@SneakyThrows
	public String getContent() {
		byte[] bytes = Files.readAllBytes(Paths.get(file.getPath()));
		return new String(bytes, encoding);
	}

}
