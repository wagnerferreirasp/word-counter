package org.example.wordcounter.app.options;

import org.junit.jupiter.api.Test;

public class GroupSizeTests extends OptionsBaseTests {

    @Test
    void groupSizeNotPassed_ShouldThrowInvalidOption() {
        String[] args = givenOptionsNotPassing(GROUP_SIZE_OPTION);

        assertInvalidOptionIsThrown(args);
    }

    @Test
    void groupSizeEquals0_ShouldThrowInvalidOption() {
        String[] args = givenValidOptionsWith(GROUP_SIZE_OPTION, "0");

        assertInvalidOptionIsThrown(args);
    }

    @Test
    void stringGroupSize_ShouldThrowInvalidOption() {
        String[] args = givenValidOptionsWith(GROUP_SIZE_OPTION, "a");

        assertInvalidOptionIsThrown(args);
    }

    @Test
    void floatGroupSize_ShouldThrowInvalidOption() {
        String[] args = givenValidOptionsWith(GROUP_SIZE_OPTION, "1.5");

        assertInvalidOptionIsThrown(args);
    }
}
