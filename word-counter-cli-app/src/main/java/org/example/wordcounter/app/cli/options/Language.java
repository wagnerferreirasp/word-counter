package org.example.wordcounter.app.cli.options;

import java.util.List;

import lombok.Getter;

@Getter
public class Language {

    public static final String CUSTOM_NAME = "CUSTOM";
    public static final Language CZ = Language.of("CZ", "Czech", "ěščřžýáíéóúůďťňĎŇŤŠČŘŽÝÁÍÉÚŮ\\p{Alpha}");
    public static final Language EN = Language.of("EN", "English", "-'éç\\p{Alpha}");
    public static final Language ES = Language.of("ES", "Spanish", "'áéíñóúüÁÉÍÑÓÚÜ\\p{Alpha}");
    public static final Language PT = Language.of("PT", "Portuguese", "-'áéíóúàãõâêüçÁÉÍÓÚÀÃÕÂÊÜÇ\\p{Alpha}");
    public static final List<Language> AVAILABLE_LANGUAGES = List.of(EN, CZ, ES, PT);

    private final String shortName;
    private final String longName;
    private final String alphabetRegex;

    private Language(String shortName, String longName, String alphabetRegex) {
        this.shortName = shortName;
        this.longName = longName;
        this.alphabetRegex = alphabetRegex;
    }

    public static Language of(String shortName, String longName, String alphabetRegex) {
        return new Language(shortName, longName, alphabetRegex);
    }

    public static Language customOf(String alphabetRegex) {
        return new Language(CUSTOM_NAME, CUSTOM_NAME, alphabetRegex);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", shortName, longName);
    }

}
