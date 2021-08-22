package org.example.wordcounter.app;

import org.example.wordcounter.app.options.Options;

import java.util.Optional;

public class ExecutionResult {
	private final Options options;
	private final String output;
	private final String outputMsg;
	private final String errorMsg;

	public ExecutionResult(Options options, String output, String outputMsg, String errorMsg) {
		this.options = options;
		this.output = output;
		this.outputMsg = outputMsg;
		this.errorMsg = errorMsg;
	}

	public ExecutionResult(String errorMsg) {
		this(null, null, null, errorMsg);
	}

	public ExecutionResult(Options options, String output, String outputMsg) {
		this(options, output, outputMsg, null);
	}

	public Optional<String> getOutputMsg() {
		return Optional.ofNullable(outputMsg);
	}

	public Optional<String> getErrorMsg() {
		return Optional.ofNullable(errorMsg);
	}

	public Optional<String> getOutput() {
		return Optional.ofNullable(output);
	}

	public Optional<Options> getOptions() {
		return Optional.ofNullable(options);
	}
}
