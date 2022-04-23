package org.example.wordcounter.app.cli.options;

import org.junit.jupiter.api.Test;

import static org.example.wordcounter.app.cli.options.Options.LANGUAGE_OPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanguageTests extends OptionsBaseTests {

    @Test
    void languageNotPassed_ShouldDefaultToEnglish() {
        String[] args = givenOptionsNotPassing(LANGUAGE_OPTION);

        Options options = optionsParser.parse(args).orElseThrow();
        assertEquals("EN", options.getLanguage().getShortName().toUpperCase());
        assertEquals("ENGLISH", options.getLanguage().getLongName().toUpperCase());
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

        Options options = optionsParser.parse(args).orElseThrow();

        assertEquals(ALPHABET_VALID_VALUE, options.getLanguage().getAlphabetRegex());
    }

    @Test
    void czechShouldBeAvailable() {
        String[] args = givenValidOptionsWith(LANGUAGE_OPTION,"CZ");

        Options options = optionsParser.parse(args).orElseThrow();

        assertEquals("CZ", options.getLanguage().getShortName().toUpperCase());
    }

    @Test
    void spanishShouldBeAvailable() {
        String[] args = givenValidOptionsWith(LANGUAGE_OPTION,"ES");

        Options options = optionsParser.parse(args).orElseThrow();

        assertEquals("ES", options.getLanguage().getShortName().toUpperCase());
    }

    @Test
    void portugueseShouldBeAvailable() {
        String[] args = givenValidOptionsWith(LANGUAGE_OPTION,"PT");

        Options options = optionsParser.parse(args).orElseThrow();

        assertEquals("PT", options.getLanguage().getShortName().toUpperCase());
    }

}
