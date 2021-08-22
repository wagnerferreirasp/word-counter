package org.example.wordcounter.app.options.jcommander;

import com.beust.jcommander.ParameterException;
import com.beust.jcommander.converters.BaseConverter;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

public class CharsetStringConverter extends BaseConverter<Charset> {
	public CharsetStringConverter(String optionName) {
		super(optionName);
	}

	@Override
	public Charset convert(String encodingName) {
		try {
			return Charset.forName(encodingName);
		} catch (UnsupportedCharsetException e) {
			throw new ParameterException(this.getErrorString(encodingName, "an encoding"));
		}
	}
}
