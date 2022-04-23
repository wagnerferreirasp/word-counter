package org.example.wordcounter.app.cli.options;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FormattedHelp {
	private String usage;
	private List<String> requiredOptions;
	private List<String> nonRequiredOptions;

	public String format() {
		return usage +
		"\n\tRequired options:" +
			String.join("\n", requiredOptions) +
		"\n\n\tNon-required options:" +
			String.join("\n", nonRequiredOptions);
	}
}
