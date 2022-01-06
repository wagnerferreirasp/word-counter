package org.example.wordcounter.app.cli.options;

import org.junit.jupiter.api.Test;

import static org.example.wordcounter.app.cli.options.Constants.LANGUAGE_OPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanguageTests extends OptionsBaseTests {

    @Test
    void languageNotPassed_ShouldDefaultToEnglish() {
        String[] args = givenOptionsNotPassing(LANGUAGE_OPTION);

        Options options = optionsParser.parse(args);
        assertEquals(Language.EN, options.getLanguage());
    }

    @Test
    void nonExistentLanguage_ShouldThrowException() {
        String[] args = givenValidOptionsWith(LANGUAGE_OPTION, "nonexistent");

        assertInvalidOptionIsThrown(args);
    }

    @Test
    void languageCustom_ShouldSpecifyAlphabet() {
        String[] args = givenValidOptionsWith(LANGUAGE_OPTION, Language.CUSTOM.getName());

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
}
