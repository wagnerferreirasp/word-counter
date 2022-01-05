package org.example.wordcounter.app.files;

import org.example.wordcounter.core.text.Text;
import org.example.wordcounter.core.text.TextProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides the Texts via Files
 */
public class FileTextProvider implements TextProvider {

	private final File path;
	private final Charset encoding;

	public FileTextProvider(File path, Charset encoding) {
		if (!path.exists()) {
			throw new UncheckedIOException(new FileNotFoundException(path.getName()));
		}
		this.path = path;
		this.encoding = encoding;
	}

	@Override
	public List<Text> findAll() {
		if (!path.isDirectory()) {
			return Collections.singletonList(getTextFromFile(path));
		}
		return Arrays.stream(path.listFiles())
				.map(this::getTextFromFile)
				.collect(Collectors.toList());
	}

	private Text getTextFromFile(File file) {
		return new FileText(file, encoding);
	}
}
