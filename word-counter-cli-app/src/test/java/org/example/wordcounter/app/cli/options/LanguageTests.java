package org.example.wordcounter.app.cli.options;

import org.junit.jupiter.api.Test;

import static org.example.wordcounter.app.cli.options.Options.Names.LANGUAGE_OPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanguageTests extends OptionsBaseTests {

    @Test
    void languageNotPassed_ShouldDefaultToEnglish() {
        String[] args = givenOptionsNotPassing(LANGUAGE_OPTION);

        Options options = optionsParser.parse(args);
        assertEquals("EN", options.getLanguage().getName().toUpperCase());
    }

    @Test
    void nonExistentLanguage_ShouldThrowException() {
        String[] args = givenValidOptionsWith(LANGUAGE_OPTION, "nonexistent");

        assertInvalidOptionIsThrown(args);
    }

    @Test
    void languageCustom_ShouldSpecifyAlphabet() {
        String[] args = givenValidOptionsWith(LANGUAGE_OPTION, "CUSTOM");

        assertInvalidOptionIsThrown(args);
    }

    @Test
    void languageCustomWithAlphabet_ShouldParseCorrectly() {
        String[] args = givenValidOptionsWith(LANGUAGE_OPTION,
            String.format("CUSTOM:%s", BASE64_ALPHABET_VALID_VALUE)
        );

        Options options = optionsParser.parse(args);

        assertEquals(ALPHABET_VALID_VALUE, options.getLanguage().getAlphabetRegex());
    }

    @Test
    void czechShouldBeAvailable() {
        String[] args = givenValidOptionsWith(LANGUAGE_OPTION,"CZ");

        Options options = optionsParser.parse(args);

        assertEquals("CZ", options.getLanguage().getName().toUpperCase());
    }

    @Test
    void spanishShouldBeAvailable() {
        String[] args = givenValidOptionsWith(LANGUAGE_OPTION,"ES");

        Options options = optionsParser.parse(args);

        assertEquals("ES", options.getLanguage().getName().toUpperCase());
    }

    @Test
    void portugueseShouldBeAvailable() {
        String[] args = givenValidOptionsWith(LANGUAGE_OPTION,"PT");

        Options options = optionsParser.parse(args);

        assertEquals("PT", options.getLanguage().getName().toUpperCase());
    }

}
