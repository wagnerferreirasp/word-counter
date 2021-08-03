package org.example.wordcounter.app.boundaries;

import org.example.wordcounter.core.boundaries.Text;

import java.io.*;
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

	public String getContent() {
		if (content == null) {
			content = new BufferedReader(new InputStreamReader(inputStream, encoding))
					.lines()
					.map(s ->
							s.replace("<i>", " ")
									.replace("</i>", " ")
									.replace("<a>", " ")
									.replace("</a>", " ")
									.replace("jingspiral@himym.cz", " ")
									.replace("HOW I MET YOUR MOTHER", " ")
									.replace("jingspiral@himym.cz", " ")
									.replace("http://himym.cz", " ")
									.replace("Překlad: jingspiral", " ")
									.replace("--", " ")
									.replace("\\n-", " ")
					)
					.collect(Collectors.joining("\n"));
		}
		return content;
	}
}
