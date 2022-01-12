package org.example.wordcounter.app.cli.options;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.example.wordcounter.app.cli.options.impl.CharsetStringConverter;
import org.example.wordcounter.app.cli.options.impl.FileExistsValidator;
import org.example.wordcounter.app.cli.options.impl.LanguageConverter;
import org.example.wordcounter.app.cli.options.impl.PositiveIntValidator;

import com.beust.jcommander.Parameter;

import lombok.Getter;

/**
 * Represents the options passed via cli to run the application
 */
@Getter
public class Options {

    @Parameter(description = Descriptions.HELP,
        names = { Names.HELP_OPTION, Names.HELP_SHORT_OPTION },
        help = true
    )
    private boolean help;

    @Parameter(description = Descriptions.INPUT_PATH,
        names = { Names.INPUT_PATH_OPTION, Names.INPUT_PATH_SHORT_OPTION },
        required = true, validateValueWith = FileExistsValidator.class
    )
    private File inputPath;

    @Parameter(description = Descriptions.OUTPUT_PATH,
        names = { Names.OUTPUT_PATH_OPTION, Names.OUTPUT_PATH_SHORT_OPTION},
        required = true
    )
    private File outputPath;

    @Parameter(description = Descriptions.GROUP_SIZE,
        names = { Names.GROUP_SIZE_OPTION, Names.GROUP_SIZE_SHORT_OPTION },
        validateWith = PositiveIntValidator.class
    )
    private Integer groupSize = 1;

    @Parameter(description = Descriptions.LANGUAGE,
        names = { Names.LANGUAGE_OPTION, Names.LANGUAGE_SHORT_OPTION },
        converter = LanguageConverter.class
    )
    private Language language = Language.EN;

    @Parameter(description = Descriptions.ENCODING,
        names = { Names.ENCODING_OPTION, Names.ENCODING_SHORT_OPTION },
        converter = CharsetStringConverter.class
    )
    private Charset encoding = StandardCharsets.UTF_8;

    public static class Names {
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
    }

    private static class Descriptions {
        public static final String HELP = "Help Command - shows the options";
        public static final String INPUT_PATH = "Input Path - file or directory to count the words";
        public static final String ENCODING = "Encoding - encoding to consider when reading "
            + "files from " + Names.INPUT_PATH_OPTION;
        public static final String OUTPUT_PATH = "Output file path - the csv location to the results";
        public static final String GROUP_SIZE = "Group size - number of words to group and count together";
        public static final String LANGUAGE = "Language - the language of the texts. " +
            "\nPossible values: CZ (Czech), EN (English), ES (Spanish), PT (Portuguese) "
            + "or a custom language by passing the list of letters encoded in base64 - "
            + "CUSTOM:<base64alphabet>";
    }

}
