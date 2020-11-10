package app;

import boundaries.Text;
import boundaries.TextProvider;

import java.util.List;

public class FileTextProvider implements TextProvider {
	private final List<Text> texts;

	public FileTextProvider(List<Text> texts) {
		this.texts = texts;
	}

	@Override
	public List<Text> findAll() {
		return texts;
	}
}
