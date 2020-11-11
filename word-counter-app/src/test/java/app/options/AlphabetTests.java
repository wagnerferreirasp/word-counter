package app.options;

import org.junit.jupiter.api.Test;

public class AlphabetTests extends OptionsBaseTests {
    @Test
    void alphabetNotPassed_ShouldThrowArgumentInvalid() {
        String[] args = givenOptionsNotPassing(ALPHABET_OPTION);

        assertInvalidOptionIsThrown(args);
    }
}
