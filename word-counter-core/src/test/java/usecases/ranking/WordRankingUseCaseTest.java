package usecases.ranking;

import boundaries.SimpleTextProviderMock;
import boundaries.Text;
import usecases.WordRankingUseCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static core.Constants.ENGLISH_ALPHABET;

public class WordRankingUseCaseTest {
    protected final SimpleTextProviderMock textProvider = new SimpleTextProviderMock();
    protected final WordRankingUseCase wordRanking = new WordRankingUseCase(
            ENGLISH_ALPHABET, textProvider
    );

    protected void givenTextsWithSameContent(int quantity, String content) {
        List<Text> texts = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            texts.add(() -> content);
        }
        textProvider.setTexts(texts);
    }

    protected void givenTexts(String... contents) {
        List<Text> texts = Arrays.stream(contents)
                .map(content -> (Text) () -> content)
                .collect(Collectors.toList());
        textProvider.setTexts(texts);
    }
}
