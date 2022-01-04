package org.example.wordcounter.app.files;

import org.example.wordcounter.core.text.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

public class InputStreamText implements Text {
	private final InputStream inputStream;
	private final Charset encoding;
	private String content;

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
		return new BufferedReader(new InputStreamReader(inputStream, encoding))
				.lines()
				.collect(Collectors.joining("\n"));
	}
}
