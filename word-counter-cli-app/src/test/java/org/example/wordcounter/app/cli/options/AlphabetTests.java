package org.example.wordcounter.app.cli.options;

import org.junit.jupiter.api.Test;

import static org.example.wordcounter.app.cli.options.Constants.ALPHABET_OPTION;

public class AlphabetTests extends OptionsBaseTests {
    @Test
    void alphabetNotPassed_ShouldThrowArgumentInvalid() {
        String[] args = givenOptionsNotPassing(ALPHABET_OPTION);

        assertInvalidOptionIsThrown(args);
    }
}
