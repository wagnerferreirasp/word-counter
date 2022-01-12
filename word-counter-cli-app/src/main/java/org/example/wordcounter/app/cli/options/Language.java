package org.example.wordcounter.app.cli.options;

import java.util.List;

import lombok.Getter;

@Getter
public class Language {

    public static final String CUSTOM_NAME = "CUSTOM";
    public static final Language CZ = Language.of("CZ", "ěščřžýáíéóúůďťňĎŇŤŠČŘŽÝÁÍÉÚŮ\\p{Alpha}");
    public static final Language EN = Language.of("EN", "-'éç\\p{Alpha}");
    public static final Language ES = Language.of("ES", "-'áéíñóúüÁÉÍÑÓÚÜ\\p{Alpha}");
    public static final Language PT = Language.of("PT", "-'áéíóúàãõâêüçÁÉÍÓÚÀÃÕÂÊÜÇ\\p{Alpha}");
    public static final List<Language> AVAILABLE_LANGUAGES = List.of(EN, CZ, ES, PT);

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
        return new Language(CUSTOM_NAME, alphabetRegex);
    }

    @Override
    public String toString() {
        return name;
    }

}
