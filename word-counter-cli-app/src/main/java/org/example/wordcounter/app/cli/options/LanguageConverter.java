package org.example.wordcounter.app.cli.options;

import java.util.Base64;

public class LanguageConverter {

    public Language convert(String value) {
        return isCustomLanguage(value)
            ? convertCustom(value)
            : convertFromAvailableLanguages(value);
    }

    private boolean isCustomLanguage(String value) {
        return value.toUpperCase().startsWith(Language.CUSTOM_NAME);
    }

    private Language convertCustom(String value) {
        String[] valueArray = value.split(":");
        if (valueArray.length < 2) {
            throw new InvalidOptionException("The alphabet should be " +
                "passed to the custom language");
        }
        byte[] alphabetBytes = Base64.getDecoder().decode(valueArray[1]);
        return Language.customOf(new String(alphabetBytes));
    }

    private Language convertFromAvailableLanguages(String value) {
        return Language.AVAILABLE_LANGUAGES.stream()
            .filter(language -> value.equalsIgnoreCase(language.getShortName()))
            .findAny()
            .orElseThrow(() -> new InvalidOptionException("No language found with name " + value));
    }
}
