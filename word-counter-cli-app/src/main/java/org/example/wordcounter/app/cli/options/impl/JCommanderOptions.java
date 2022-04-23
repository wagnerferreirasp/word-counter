package org.example.wordcounter.app.cli.options.impl;

import java.io.File;
import java.util.Optional;

import org.example.wordcounter.app.cli.options.Options;

import com.beust.jcommander.Parameter;

public class JCommanderOptions {

    @Parameter(description = Options.HELP_DESCRIPTION,
        names = { Options.HELP_OPTION, Options.HELP_SHORT_OPTION },
        help = true
    )
    private boolean help;

    @Parameter(description = Options.INPUT_PATH_DESCRIPTION,
        names = { Options.INPUT_PATH_OPTION, Options.INPUT_PATH_SHORT_OPTION },
        required = true
    )
    private File inputPath;

    @Parameter(description = Options.OUTPUT_PATH_DESCRIPTION,
        names = { Options.OUTPUT_PATH_OPTION, Options.OUTPUT_PATH_SHORT_OPTION},
        required = true
    )
    private File outputPath;

    @Parameter(description = Options.GROUP_SIZE_DESCRIPTION,
        names = { Options.GROUP_SIZE_OPTION, Options.GROUP_SIZE_SHORT_OPTION }
    )
    private Integer groupSize = 1;

    @Parameter(description = Options.LANGUAGE_DESCRIPTION,
        names = { Options.LANGUAGE_OPTION, Options.LANGUAGE_SHORT_OPTION }
    )
    private String language = "EN";

    @Parameter(description = Options.ENCODING_DESCRIPTION,
        names = { Options.ENCODING_OPTION, Options.ENCODING_SHORT_OPTION }
    )
    private String encoding = "UTF-8";

    Optional<Options> toOptions() {
        if (help) {
            return Optional.empty();
        }
        return Optional.of(new Options(
            inputPath,
            outputPath,
            groupSize,
            language,
            encoding
        ));
    }

}
