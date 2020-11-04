package usecases.ranking;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RankingSumTests extends WordRankingUseCaseTest {
    @Test
    void twoSimpleTextWithSameText_ShouldCountInDouble() {
        givenTextsWithSameContent(2, "are you you");

        Map<String, Integer> actual = wordRanking.rankWordsFromTexts(1);

        assertThat(actual.get("you"), is(4));
        assertThat(actual.get("are"), is(2));
    }

    @Test
    void twoEqualTexts_ShouldBeDoubleOfEachWord() {
        givenTextsWithSameContent(2, "you are");

        Map<String, Integer> actual = wordRanking.rankWordsFromTexts(1);

        assertThat(actual.get("you"), is(2));
        assertThat(actual.get("are"), is(2));
    }

    @Test
    void threeEqualTexts_ShouldBeTripleOfEachWord() {
        givenTextsWithSameContent(3, "you are");

        Map<String, Integer> actual = wordRanking.rankWordsFromTexts(1);

        assertThat(actual.get("you"), is(3));
        assertThat(actual.get("are"), is(3));
    }

    @Test
    void twoDifferentTexts_ShouldSumCorrectly() {
        givenTexts("you are ok", "who are you");

        Map<String, Integer> actual = wordRanking.rankWordsFromTexts(1);

        assertThat(actual.get("you"), is(2));
        assertThat(actual.get("are"), is(2));
        assertThat(actual.get("ok"), is(1));
    }

    @Test
    void pairOfWordsInTwoDifferentTexts_ShouldSumCorrectly() {
        givenTexts("you are ok", "who are you are");

        Map<String, Integer> actual = wordRanking.rankWordsFromTexts(2);

        assertThat(actual.get("you are"), is(2));
        assertThat(actual.get("are ok"), is(1));
        assertThat(actual.get("who are"), is(1));
        assertThat(actual.get("are you"), is(1));
    }
}
