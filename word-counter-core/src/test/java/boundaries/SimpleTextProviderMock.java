package boundaries;

import java.util.List;

public class SimpleTextProviderMock implements TextProvider {
    private List<Text> texts;

    @Override
    public List<Text> findAll() {
        return texts;
    }

    public void setTexts(List<Text> texts) {
        this.texts = texts;
    }
}
