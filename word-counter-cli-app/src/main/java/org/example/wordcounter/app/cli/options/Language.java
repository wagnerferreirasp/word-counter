package org.example.wordcounter.app.cli.options;

import lombok.Getter;

@Getter
public class Language {

    private final String name;
    private final String alphabetRegex;

    private Language(String name, String alphabetRegex) {
        this.name = name;
        this.alphabetRegex = alphabetRegex;
    }

    public static Language of(String name, String alphabetRegex) {
        return new Language(name, alphabetRegex);
    }

    public static Language customOf(String alphabetRegex) {
        return new Language(Constants.CUSTOM_LANGUAGE_NAME, alphabetRegex);
    }

    @Override
    public String toString() {
        return name;
    }

}
