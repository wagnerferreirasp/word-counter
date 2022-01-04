package org.example.wordcounter.app.cli.options;

public interface OptionsParser {
	Options parse(String[] args);
	String getHelp();
}
