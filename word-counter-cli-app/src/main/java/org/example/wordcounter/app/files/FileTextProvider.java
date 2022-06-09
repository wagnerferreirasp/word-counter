package org.example.wordcounter.app.files;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.example.wordcounter.core.text.Text;
import org.example.wordcounter.core.text.TextProvider;

import lombok.SneakyThrows;

/**
 * Provides the Texts via Files
 */
public class FileTextProvider implements TextProvider {

	private final File path;
	private final Charset encoding;

	@SneakyThrows
	public FileTextProvider(File path, Charset encoding) {
		if (!path.exists()) {
			throw new NoSuchFileException(path.getName());
		}
		this.path = path;
		this.encoding = encoding;
	}

	@Override
	public List<Text> findAll() {
		return findTextsRecursively(path)
			.collect(Collectors.toList());
	}

	private Stream<Text> findTextsRecursively(File path) {
		return !path.isDirectory()
			? Stream.of(new FileText(path, encoding))
			: Arrays.stream(path.listFiles())
				.flatMap(this::findTextsRecursively);
	}

}
