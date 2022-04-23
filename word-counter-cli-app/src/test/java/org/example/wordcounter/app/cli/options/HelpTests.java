package org.example.wordcounter.app.cli.options;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelpTests extends OptionsBaseTests {

	@Test
	void helpShouldContainRequiredOptions() {
		FormattedHelp help = optionsParser.getHelp();

		assertEquals(help.getRequiredOptions().size(), 2);
		assertContainsOption(help.getRequiredOptions(), Options.INPUT_PATH_OPTION);
		assertContainsOption(help.getRequiredOptions(), Options.INPUT_PATH_SHORT_OPTION);
		assertContainsOption(help.getRequiredOptions(), Options.OUTPUT_PATH_OPTION);
		assertContainsOption(help.getRequiredOptions(), Options.OUTPUT_PATH_SHORT_OPTION);
	}

	@Test
	void helpShouldContainNonRequiredOptions() {
		FormattedHelp help = optionsParser.getHelp();

		assertEquals(help.getNonRequiredOptions().size(), 4);
		assertContainsOption(help.getNonRequiredOptions(), Options.ENCODING_OPTION);
		assertContainsOption(help.getNonRequiredOptions(), Options.ENCODING_SHORT_OPTION);
		assertContainsOption(help.getNonRequiredOptions(), Options.GROUP_SIZE_OPTION);
		assertContainsOption(help.getNonRequiredOptions(), Options.GROUP_SIZE_SHORT_OPTION);
		assertContainsOption(help.getNonRequiredOptions(), Options.HELP_OPTION);
		assertContainsOption(help.getNonRequiredOptions(), Options.HELP_SHORT_OPTION);
		assertContainsOption(help.getNonRequiredOptions(), Options.LANGUAGE_OPTION);
		assertContainsOption(help.getNonRequiredOptions(), Options.LANGUAGE_SHORT_OPTION);
	}

	private void assertContainsOption(List<String> options, String option) {
		assertTrue(options.stream()
			.anyMatch(output -> output.contains(option)));
	}

}
