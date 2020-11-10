package usecases.ranking;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RankingGroupSizeTests extends WordRankingUseCaseTest {
    @Test
    void singleEmptyText_ShouldBeEmptyMap() {
        givenTextsWithSameContent(1,"");

        Map<String, Integer> actual = wordRanking.rankWordsFromTexts(1);

        assertThat(actual.keySet(), is(empty()));
    }

    @Test
    void twoEmptyTexts_ShouldBeEmptyMap() {
        givenTextsWithSameContent(2, "");

        Map<String, Integer> actual = wordRanking.rankWordsFromTexts(1);

        assertThat(actual.keySet(), is(empty()));
    }

    @Test
    void twoWords_ShouldGroupThemTogether() {
        givenTexts( "you are");

        Map<String, Integer> actual = wordRanking.rankWordsFromTexts(2);

        assertThat(actual.keySet(), hasItem("you are"));
    }

    @Test
    void threeWords_ShouldGroupThemTogether() {
        givenTexts( "you are ok");

        Map<String, Integer> actual = wordRanking.rankWordsFromTexts(3);

        assertThat(actual.keySet(), hasItem("you are ok"));
    }
}
