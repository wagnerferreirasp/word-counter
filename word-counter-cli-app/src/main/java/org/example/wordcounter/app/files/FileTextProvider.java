package org.example.wordcounter.app.files;

import lombok.SneakyThrows;
import org.example.wordcounter.core.text.Text;
import org.example.wordcounter.core.text.TextProvider;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
		return findTextsFromPath(path);
	}

	private List<Text> findTextsFromPath(File path) {
		if (!path.isDirectory()) {
			return List.of(new FileText(path, encoding));
		}
		return Arrays.stream(path.listFiles())
			.map(this::findTextsFromPath)
			.flatMap(Collection::stream)
			.collect(Collectors.toList());
	}

}
