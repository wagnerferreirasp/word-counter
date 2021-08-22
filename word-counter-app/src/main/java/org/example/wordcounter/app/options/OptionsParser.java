package org.example.wordcounter.app.options;

public interface OptionsParser {
	Options parse(String[] argv);
	String getHelp();
}
