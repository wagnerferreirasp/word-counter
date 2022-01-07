package org.example.wordcounter.app.cli.options.impl;

import com.beust.jcommander.ParameterException;
import com.beust.jcommander.converters.BaseConverter;
import org.example.wordcounter.app.cli.options.Constants;
import org.example.wordcounter.app.cli.options.Language;

import java.util.Base64;

public class LanguageConverter extends BaseConverter<Language> {
    public LanguageConverter(String optionName) {
        super(optionName);
    }

    @Override
    public Language convert(String value) {
        if (value.toUpperCase().startsWith(Constants.CUSTOM_LANGUAGE_NAME)) {
            return convertCustom(value);
        }

        return Constants.AVAILABLE_LANGUAGES.stream()
            .filter(language -> value.equalsIgnoreCase(language.getName()))
            .findAny()
            .orElseThrow(() -> new ParameterException("No language found with name " + value));
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
