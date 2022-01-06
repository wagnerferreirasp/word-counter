package org.example.wordcounter.app.cli.options;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = {"name"})
public class Language {
    public static final Language EN = fromEnum(LanguageEnum.EN);
    public static final Language CUSTOM = fromEnum(LanguageEnum.CUSTOM);

    private final String name;
    private final String alphabetRegex;

    private Language(String name, String alphabetRegex) {
        this.name = name;
        this.alphabetRegex = alphabetRegex;
    }

    public static Language of(String name) {
        LanguageEnum languageEnum = LanguageEnum.of(name);
        return new Language(languageEnum.toString(), languageEnum.getAlphabetRegex());
    }

    public static Language customOf(String alphabetRegex) {
        return new Language(LanguageEnum.CUSTOM.toString(), alphabetRegex);
    }

    private static Language fromEnum(LanguageEnum languageEnum) {
        return new Language(languageEnum.toString(), languageEnum.getAlphabetRegex());
    }

    @Override
    public String toString() {
        return name;
    }

    @Getter
    private enum LanguageEnum {
        EN("-éç'\\p{Alpha}"),
        CUSTOM(null);

        private final String alphabetRegex;

        LanguageEnum(String alphabetRegex) {
            this.alphabetRegex = alphabetRegex;
        }

        static LanguageEnum of(String name) {
            return valueOf(name.toUpperCase());
        }
    }
}
