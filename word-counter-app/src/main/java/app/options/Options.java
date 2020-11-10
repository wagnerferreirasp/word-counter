package app.options;

import app.options.validators.PositiveIntValidator;
import com.beust.jcommander.Parameter;

import java.io.File;

public class Options {
    @Parameter(description = "Group size - number of words to group and count together",
        names = { "--group-size"}, required = true, validateWith = PositiveIntValidator.class
    )
    public Integer groupSize;

    @Parameter(description = "Alphabet - list of letters to consider when searching words",
            names = { "--alphabet"}, required = true
    )
    public String alphabet;

    @Parameter(description = "Path - file or directory to count the words",
            names = { "--path"}, required = true
    )
    public File path;
}
