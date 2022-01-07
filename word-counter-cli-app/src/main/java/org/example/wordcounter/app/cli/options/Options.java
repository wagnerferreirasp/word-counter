package org.example.wordcounter.app.cli.options;

import com.beust.jcommander.Parameter;
import lombok.Getter;
import org.example.wordcounter.app.cli.options.impl.CharsetStringConverter;
import org.example.wordcounter.app.cli.options.impl.FileExistsValidator;
import org.example.wordcounter.app.cli.options.impl.LanguageConverter;
import org.example.wordcounter.app.cli.options.impl.PositiveIntValidator;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.example.wordcounter.app.cli.options.Constants.ENCODING_OPTION;
import static org.example.wordcounter.app.cli.options.Constants.GROUP_SIZE_OPTION;
import static org.example.wordcounter.app.cli.options.Constants.LANGUAGE_OPTION;
import static org.example.wordcounter.app.cli.options.Constants.OUTPUT_PATH_OPTION;
import static org.example.wordcounter.app.cli.options.Constants.INPUT_PATH_OPTION;

/**
 * Represents the options passed via cli to run the application
 */
@Getter
public class Options {

    @Parameter(description = "Help Command - shows the options", names = "--help", help = true)
    private boolean help;

    @Parameter(description = "Input Path - file or directory to count the words",
        names = {INPUT_PATH_OPTION}, required = true, validateValueWith = FileExistsValidator.class
    )
    private File inputPath;

    @Parameter(description = "Output file path - the csv location to the results",
        names = {OUTPUT_PATH_OPTION}, required = true
    )
    private File outputPath;

    @Parameter(description = "Group size - number of words to group and count together",
        names = {GROUP_SIZE_OPTION}, validateWith = PositiveIntValidator.class
    )
    private Integer groupSize = 1;

    @Parameter(description = "Language - the language of the texts. " +
        "\nPossible values: CZ (Czech), EN (English), ES (Spanish) or a custom language by " +
        "passing the list of letters encoded in base64 - CUSTOM:<base64alphabet>",
        names = {LANGUAGE_OPTION}, converter = LanguageConverter.class
    )
    private Language language = Language.EN;

    @Parameter(description = "Encoding - encoding to consider when reading files from " + INPUT_PATH_OPTION,
        names = {ENCODING_OPTION}, converter = CharsetStringConverter.class
    )
    private Charset encoding = StandardCharsets.UTF_8;

}
