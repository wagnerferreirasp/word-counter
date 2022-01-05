package org.example.wordcounter.app.files;

import org.example.wordcounter.core.text.Text;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Implements a Text using a File
 */
public class FileText implements Text {
	private final File file;
	private final Charset encoding;
	private String content;

	public FileText(File file, Charset encoding) {
		this.file = file;
		this.encoding = encoding;
	}

	@Override
	public String getContent() {
		if (content == null) {
			content = readFile();
		}
		return content;
	}

	private String readFile() {
		try {
			byte[] bytes = Files.readAllBytes(Paths.get(file.getPath()));
			return new String(bytes, encoding);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
