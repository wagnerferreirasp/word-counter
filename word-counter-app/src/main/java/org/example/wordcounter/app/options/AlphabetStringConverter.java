package org.example.wordcounter.app.options;

import com.beust.jcommander.converters.BaseConverter;

import java.util.Base64;

public class AlphabetStringConverter extends BaseConverter<String> {
	public AlphabetStringConverter(String optionName) {
		super(optionName);
	}

	@Override
	public String convert(String base64Alphabet) {
		byte[] decodedBytes = Base64.getDecoder().decode(base64Alphabet);
		return new String(decodedBytes);
	}
}
