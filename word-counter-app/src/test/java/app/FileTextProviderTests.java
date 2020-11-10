package app;

import boundaries.Text;
import boundaries.TextProvider;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileTextProviderTests {
	@Test
	void emptyList_FindAllShouldBeEmpty() {
		TextProvider textProvider = new FileTextProvider(Collections.emptyList());

		List<Text> all = textProvider.findAll();

		assertEquals(Collections.emptyList(), all);
	}

	@Test
	void singleText_FindAllShouldReturnIt() {
		Text text = TextUtils.givenSimpleTextFile();
		TextProvider textProvider = new FileTextProvider(Collections.singletonList(text));

		List<Text> all = textProvider.findAll();

		assertEquals(Collections.singletonList(text), all);
	}

	@Test
	void twoFiles_FindAllShouldReturnThem() {
		Text complexFile = TextUtils.givenComplexFile();
		Text cp1250File = TextUtils.givenFileWithCp1250();
		List<Text> texts = Arrays.asList(complexFile, cp1250File);

		TextProvider textProvider = new FileTextProvider(texts);
		List<Text> all = textProvider.findAll();

		assertEquals(texts, all);
	}
}
