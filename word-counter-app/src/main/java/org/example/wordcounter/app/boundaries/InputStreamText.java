package org.example.wordcounter.app.boundaries;

import org.example.wordcounter.core.boundaries.Text;

import java.io.*;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

public class InputStreamText implements Text {
	private final InputStream inputStream;
	private final Charset encoding;

	public InputStreamText(InputStream inputStream, Charset encoding) {
		this.inputStream = inputStream;
		this.encoding = encoding;
	}

	public String getContent() {
		return new BufferedReader(new InputStreamReader(inputStream, encoding))
				.lines()
				.collect(Collectors.joining("\n"));
	}
}
