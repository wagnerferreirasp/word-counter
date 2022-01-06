package org.example.wordcounter.app.cli.options.impl;

import com.beust.jcommander.ParameterException;
import com.beust.jcommander.converters.BaseConverter;
import org.example.wordcounter.app.cli.options.Language;

import java.util.Base64;

public class LanguageConverter extends BaseConverter<Language> {
    public LanguageConverter(String optionName) {
        super(optionName);
    }

    @Override
    public Language convert(String value) {
        try {
            if (value.toUpperCase().startsWith("CUSTOM")) {
                return convertCustom(value);
            }
            return Language.of(value);
        } catch (IllegalArgumentException e) {
            throw new ParameterException("Language not found!");
        }
    }

    private Language convertCustom(String value) {
        String[] valueArray = value.split(":");
        if (valueArray.length < 2) {
            throw new ParameterException("Option " + getOptionName() + " invalid. The alphabet should be " +
                "passed to the custom language");
        }
        byte[] alphabetBytes = Base64.getDecoder().decode(valueArray[1]);
        return Language.customOf(new String(alphabetBytes));
    }
}
