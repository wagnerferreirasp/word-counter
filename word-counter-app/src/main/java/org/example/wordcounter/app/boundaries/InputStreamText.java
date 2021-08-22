package org.example.wordcounter.app.boundaries;

import org.example.wordcounter.core.boundaries.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InputStreamText implements Text {
	private final InputStream inputStream;
	private final Charset encoding;
	private InputStream exclusions;
	private String content;

	public InputStreamText(InputStream inputStream, InputStream exclusions, Charset encoding) {
		this.inputStream = inputStream;
		this.exclusions = exclusions;
		this.encoding = encoding;
	}

	public InputStreamText(InputStream inputStream, Charset encoding) {
		this.inputStream = inputStream;
		this.encoding = encoding;
	}

	@Override
	public String getContent() {
		if (content == null) {
			content = readStream();
		}
		return content;
	}

	private String readStream() {
		List<String> exclusions = getExclusions();
		return new BufferedReader(new InputStreamReader(inputStream, encoding))
				.lines()
				.map(line -> {
					for (String exclusion : exclusions) {
						line = line.replaceAll(exclusion, " ");
					}
					return line;
				})
				.collect(Collectors.joining("\n"));
	}

	private List<String> getExclusions() {
		if (exclusions == null) {
			return Collections.emptyList();
		}

		return new BufferedReader(new InputStreamReader(exclusions, encoding))
				.lines().collect(Collectors.toList());
	}
}
