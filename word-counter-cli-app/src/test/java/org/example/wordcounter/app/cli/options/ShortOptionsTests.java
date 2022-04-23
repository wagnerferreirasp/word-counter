package org.example.wordcounter.app.cli.options;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.example.wordcounter.app.files.FileTestUtils;
import org.junit.jupiter.api.Test;

import static org.example.wordcounter.app.cli.options.Options.ENCODING_SHORT_OPTION;
import static org.example.wordcounter.app.cli.options.Options.GROUP_SIZE_SHORT_OPTION;
import static org.example.wordcounter.app.cli.options.Options.HELP_SHORT_OPTION;
import static org.example.wordcounter.app.cli.options.Options.INPUT_PATH_SHORT_OPTION;
import static org.example.wordcounter.app.cli.options.Options.LANGUAGE_SHORT_OPTION;
import static org.example.wordcounter.app.cli.options.Options.OUTPUT_PATH_SHORT_OPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShortOptionsTests extends OptionsBaseTests {

	@Test
	void testGroupSize() {
		String argValue = "2";
		testShortOptionParse(GROUP_SIZE_SHORT_OPTION, argValue,
			2, Options::getGroupSize
		);
	}

	@Test
	void testLanguage() {
		String argValue = "pt";
		testShortOptionParse(LANGUAGE_SHORT_OPTION, argValue,
			Language.PT, Options::getLanguage
		);
	}

	@Test
	void testEncoding() {
		String argValue = "UTF-16";
		testShortOptionParse(ENCODING_SHORT_OPTION, argValue,
			Charset.forName(argValue), Options::getEncoding
		);
	}

	@Test
	void testInputPath() {
		String argValue = FileTestUtils.getFullPath("texts/input1.txt");
		testShortOptionParse(INPUT_PATH_SHORT_OPTION, argValue,
			new File(argValue), Options::getInputPath
		);
	}

	@Test
	void testHelp() {
		Optional<Options> optionalOptions = optionsParser.parse(new String[] { HELP_SHORT_OPTION });
		assertTrue(optionalOptions.isEmpty());
	}

	@Override
	protected List<String> getValidRequiredArgs() {
		return new ArrayList<>(List.of(
			INPUT_PATH_SHORT_OPTION, FileTestUtils.getFullPath(INPUT_PATH_VALID_VALUE),
			OUTPUT_PATH_SHORT_OPTION, FileTestUtils.getFullPath(OUTPUT_PATH_VALID_VALUE)
		));
	}

	private <T> void testShortOptionParse(String option, String argValue, T expectedValue,
		Function<Options, T> optionGetter) {
		String[] args = givenValidOptionsWith(option, argValue);
		testShortOptionParse(args, expectedValue, optionGetter);
	}

	private <T> void testShortOptionParse(String[] args, T expectedValue, Function<Options, T> optionGetter) {
		Options options = optionsParser.parse(args).orElseThrow();
		assertEquals(optionGetter.apply(options), expectedValue);
	}

}
