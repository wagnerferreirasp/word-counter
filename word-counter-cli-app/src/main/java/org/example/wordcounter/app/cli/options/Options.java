package org.example.wordcounter.app.cli.options;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.nio.charset.Charset;

/**
 * Represents the options passed via cli to run the application
 */
@Getter
@AllArgsConstructor
public class Options {

    private final boolean help;
    private final File inputPath;
    private final File outputPath;
    private final Integer groupSize;
    private final Language language;
    private final Charset encoding;

    public static final String INPUT_PATH_OPTION = "--input-path";
    public static final String INPUT_PATH_SHORT_OPTION = "-i";
    public static final String OUTPUT_PATH_OPTION = "--output-path";
    public static final String OUTPUT_PATH_SHORT_OPTION = "-o";
    public static final String ENCODING_OPTION = "--encoding";
    public static final String ENCODING_SHORT_OPTION = "-e";
    public static final String GROUP_SIZE_OPTION = "--group-size";
    public static final String GROUP_SIZE_SHORT_OPTION = "-g";
    public static final String HELP_OPTION = "--help";
    public static final String HELP_SHORT_OPTION = "-h";
    public static final String LANGUAGE_OPTION = "--language";
    public static final String LANGUAGE_SHORT_OPTION = "-l";

    public static final String HELP_DESCRIPTION = "Shows Help - shows how to use the application with its options";
    public static final String INPUT_PATH_DESCRIPTION = "Input Path - file or directory to count the words. "
        + "In case of a directory, it will consider all files recursively";
    public static final String ENCODING_DESCRIPTION = "Encoding - encoding to consider when reading files from " + INPUT_PATH_OPTION;
    public static final String OUTPUT_PATH_DESCRIPTION = "Output file path - the csv location to the results";
    public static final String GROUP_SIZE_DESCRIPTION = "Group size - number of words to group and count together";
    public static final String LANGUAGE_DESCRIPTION = "Language - the language of the texts. "
        + "\nPossible values: %s"
        + "\nor a custom language by passing the list of letters encoded in base64 - "
        + "CUSTOM:<base64alphabet>";

}
