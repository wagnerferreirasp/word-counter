package org.example.wordcounter.app.options;

import com.beust.jcommander.Parameter;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Options {
    @Parameter(description = "Group size - number of words to group and count together",
        names = { "--group-size"}, required = true, validateWith = PositiveIntValidator.class
    )
    private Integer groupSize;

    @Parameter(description = "Alphabet - list of letters to consider when searching words",
            names = { "--alphabet"}, required = true
    )
    private String alphabet;

    @Parameter(description = "Path - file or directory to count the words",
            names = { "--path"}, required = true
    )
    private File path;

    @Parameter(description = "Encoding - encoding to consider when reading files from --path",
            names = { "--encoding"}, converter = CharsetStringConverter.class
    )
    private Charset encoding = StandardCharsets.UTF_8;

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
}
