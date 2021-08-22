package org.example.wordcounter.app.boundaries;

import org.example.wordcounter.core.boundaries.Text;
import org.example.wordcounter.core.boundaries.TextProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileTextProvider implements TextProvider {
	private final File path;
	private File exclusionsFile;
	private final Charset encoding;


	public FileTextProvider(File path, Charset encoding) {
		this.path = path;
		this.encoding = encoding;
	}

	public FileTextProvider(File path, File exclusionsFile, Charset encoding) {
		this.path = path;
		this.exclusionsFile = exclusionsFile;
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
		try {
			return exclusionsFile == null ?
					new InputStreamText(new FileInputStream(file), encoding)
					: new InputStreamText(new FileInputStream(file), new FileInputStream(exclusionsFile), encoding);
		} catch (FileNotFoundException e) {
			throw new PathNotFoundException(e);
		}
	}
}
