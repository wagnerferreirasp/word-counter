package org.example.wordcounter.app.cli.options;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelpTests extends OptionsBaseTests {

	@Test
	void helpUsageShouldContainAppName() {
		FormattedHelp help = optionsParser.getHelp();

		assertThat(help.getUsage(), containsString("word-counter-cli-app"));
	}

	@Test
	void helpShouldContainAvailableLanguages() {
		FormattedHelp help = optionsParser.getHelp();

		assertThat(help.format(), containsString(Language.CZ.getShortName()));
		assertThat(help.format(), containsString(Language.CZ.getLongName()));
		assertThat(help.format(), containsString(Language.PT.getShortName()));
		assertThat(help.format(), containsString(Language.PT.getLongName()));
		assertThat(help.format(), containsString(Language.EN.getShortName()));
		assertThat(help.format(), containsString(Language.EN.getLongName()));
	}

	@Test
	void helpShouldShowDefaultValues() {
		FormattedHelp help = optionsParser.getHelp();
		assertContainsDefault(help, Options.ENCODING_OPTION, "UTF-8");
		assertContainsDefault(help, Options.GROUP_SIZE_OPTION, "1");
		assertContainsDefault(help, Options.LANGUAGE_OPTION, "EN");
	}

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

	private void assertContainsDefault(FormattedHelp help, String option, String defaultValue) {
		assertTrue(help.getNonRequiredOptions().stream()
			.filter(opt -> opt.contains(option))
			.anyMatch(opt -> opt.contains(defaultValue)));
	}

}
