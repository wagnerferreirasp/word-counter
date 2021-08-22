package org.example.wordcounter.app;

import org.example.wordcounter.app.options.Options;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Application {

	public static void main(String[] args) {
		ExecutionResult result = new AppRunner().run(args);
		result.getOutputMsg().ifPresent(System.out::println);
		result.getErrorMsg().ifPresent(System.err::println);
		result.getOptions().ifPresent(options -> {
			try {
				result.getOutput().ifPresent(output -> writeToOutputFile(output, options));
			} catch (FileNotWritableException e) {
				System.err.printf("Error writing to the file '%s'\n", options.getPath());
			}
		});
	}

	private static void writeToOutputFile(String content, Options options) {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(options.getOutput(), false), options.getEncoding()))) {
			bw.write(content);
		} catch (IOException e) {
			throw new FileNotWritableException(e);
		}
	}
}