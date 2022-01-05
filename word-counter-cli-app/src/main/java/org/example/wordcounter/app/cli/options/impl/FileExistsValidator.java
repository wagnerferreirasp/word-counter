package org.example.wordcounter.app.cli.options.impl;

import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;

public class FileExistsValidator implements IValueValidator<File> {

    @Override
    public void validate(String name, File file) throws ParameterException {
        if (!file.exists()) {
            throw new ParameterException("Parameter " + name + " invalid. File does not exist");
        }
    }
}
