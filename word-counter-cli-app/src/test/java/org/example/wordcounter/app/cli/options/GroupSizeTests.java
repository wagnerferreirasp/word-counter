package org.example.wordcounter.app.cli.options;

import org.junit.jupiter.api.Test;

import static org.example.wordcounter.app.cli.options.Options.GROUP_SIZE_OPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupSizeTests extends OptionsBaseTests {

    @Test
    void groupSizeNotPassed_ShouldDefaultToOne() {
        String[] args = givenOptionsNotPassing(GROUP_SIZE_OPTION);

        Options options = optionsParser.parse(args);
        assertEquals(1, options.getGroupSize());
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

    @Test
    void groupSize2_ShouldParseCorrectly() {
        String[] args = givenValidOptionsWith(GROUP_SIZE_OPTION, "2");

        Options options = optionsParser.parse(args);
        assertEquals(2, options.getGroupSize());
    }
}
