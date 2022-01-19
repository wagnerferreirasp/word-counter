package org.example.wordcounter.app.cli.options.impl;

import com.beust.jcommander.Parameter;
import org.example.wordcounter.app.cli.options.Language;
import org.example.wordcounter.app.cli.options.Options;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class JCommanderParameters {

    @Parameter(description = Options.HELP_DESCRIPTION,
        names = { Options.HELP_OPTION, Options.HELP_SHORT_OPTION },
        help = true
    )
    private boolean help;

    @Parameter(description = Options.INPUT_PATH_DESCRIPTION,
        names = { Options.INPUT_PATH_OPTION, Options.INPUT_PATH_SHORT_OPTION },
        required = true,
        validateValueWith = FileExistsValidator.class
    )
    private File inputPath;

    @Parameter(description = Options.OUTPUT_PATH_DESCRIPTION,
        names = { Options.OUTPUT_PATH_OPTION, Options.OUTPUT_PATH_SHORT_OPTION},
        required = true
    )
    private File outputPath;

    @Parameter(description = Options.GROUP_SIZE_DESCRIPTION,
        names = { Options.GROUP_SIZE_OPTION, Options.GROUP_SIZE_SHORT_OPTION },
        validateWith = PositiveIntValidator.class
    )
    private Integer groupSize = 1;

    @Parameter(description = Options.LANGUAGE_DESCRIPTION,
        names = { Options.LANGUAGE_OPTION, Options.LANGUAGE_SHORT_OPTION },
        converter = LanguageConverter.class
    )
    private Language language = Language.EN;

    @Parameter(description = Options.ENCODING_DESCRIPTION,
        names = { Options.ENCODING_OPTION, Options.ENCODING_SHORT_OPTION },
        converter = CharsetStringConverter.class
    )
    private Charset encoding = StandardCharsets.UTF_8;

    Options toOptions() {
        return new Options(
            help,
            inputPath,
            outputPath,
            groupSize,
            language,
            encoding
        );
    }

}
