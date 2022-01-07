package org.example.wordcounter.app.cli.options;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
public class Language {
    public static final Language EN = of("EN", "-éç'\\p{Alpha}");
    public static final Language CZ = of("CZ", "ěščřžýáíéóúůďťňĎŇŤŠČŘŽÝÁÍÉÚŮ\\p{Alpha}");
    public static final Language ES = of("ES", "áéíñóúüÁÉÍÑÓÚÜ\\p{Alpha}");
    private static final List<Language> availableLanguages = List.of(EN, CZ, ES);

    private final String name;
    private final String alphabetRegex;

    private Language(String name, String alphabetRegex) {
        this.name = name;
        this.alphabetRegex = alphabetRegex;
    }

    private static Language of(String name, String alphabetRegex) {
        return new Language(name, alphabetRegex);
    }

    public static Language of(String name) {
        return availableLanguages.stream()
                .filter(language -> name.equalsIgnoreCase(language.getName()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No language found with name " + name));
    }

    public static Language customOf(String alphabetRegex) {
        return new Language("CUSTOM", alphabetRegex);
    }

    @Override
    public String toString() {
        return name;
    }
}
