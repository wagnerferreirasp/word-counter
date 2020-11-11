package app;

import boundaries.Text;
import boundaries.TextProvider;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static app.InputStreamTextTests.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileTextProviderTests {
	@Test
	void simpleFile_ShouldLoadCorrectly() {
		TextProvider textProvider = new FileTextProvider(givenSimpleFile(), StandardCharsets.UTF_8);

		List<Text> all = textProvider.findAll();

		assertEquals(1, all.size());
		assertEquals(CONTENT_OF_SIMPLE_TXT, all.get(0).getContent());
	}

	@Test
	void simpleFolder_ShouldLoadCorrectly() {
		TextProvider textProvider = new FileTextProvider(givenSimpleFolder(), StandardCharsets.UTF_8);

		List<Text> all = textProvider.findAll();

		Set<String> actualContents = all.stream().map(Text::getContent).collect(Collectors.toSet());
		Set<String> expectedContents = new HashSet<>(
			Arrays.asList(CONTENT_OF_SIMPLE_TXT, CONTENT_OF_EMPTY_TXT, CONTENT_OF_COMPLEX_TXT)
		);

		assertEquals(3, all.size());
		assertEquals(expectedContents, actualContents);
	}

	@NotNull
	private File givenSimpleFile() {
		return givenFile("texts/utf8/simple.txt");
	}

	@NotNull
	private File givenSimpleFolder() {
		return givenFile("texts/utf8");
	}

	@NotNull
	private File givenFile(String name) {
		return new File(getClass().getClassLoader().getResource(name).getPath());
	}

}
