package org.example.wordcounter.app.options;

import com.beust.jcommander.Parameter;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.example.wordcounter.app.Constants.*;

public class Options {
    @Parameter(names = "--help", help = true)
    private boolean help;

    @Parameter(description = "Group size - number of words to group and count together",
        names = {GROUP_SIZE_OPTION}, required = true, validateWith = PositiveIntValidator.class
    )
    private Integer groupSize;

    @Parameter(description = "Alphabet - encoded in base64 list of letters to consider when searching words",
            names = {ALPHABET_OPTION}, required = true, converter = AlphabetStringConverter.class
    )
    private String alphabet;

    @Parameter(description = "Path - file or directory to count the words",
            names = {PATH_OPTION}, required = true
    )
    private File path;

    @Parameter(description = "Encoding - encoding to consider when reading files from --path",
            names = {ENCODING_OPTION}, converter = CharsetStringConverter.class
    )
    private Charset encoding = StandardCharsets.UTF_8;

    @Parameter(description = "Output file",
            names = {OUTPUT_OPTION}, required = false
    )
    private File output;

    public Options() {
    }

    public Integer getGroupSize() {
        return groupSize;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public File getPath() {
        return path;
    }

    public Charset getEncoding() {
        return encoding;
    }

    public File getOutput() {
        return output;
    }

    public boolean isHelp() {
        return help;
    }
}
