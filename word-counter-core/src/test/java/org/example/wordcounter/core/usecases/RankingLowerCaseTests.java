package org.example.wordcounter.core.usecases;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class RankingLowerCaseTests extends WordRankingUseCaseTest {
    @Test
    void singleSimpleTextWithUpperCase_ShouldLowerTheCase() {
        givenTextsWithSameContent(1,"Are You OK?");

        Map<String, Integer> actual = wordRanking.rankWordsFromTexts(1);

        assertThat(actual.keySet(), hasItem("are"));
        assertThat(actual.keySet(), hasItem("you"));
        assertThat(actual.keySet(), hasItem("ok"));
    }

    @Test
    void allCaps_ShouldLowerTheCase() {
        givenTextsWithSameContent(1,"ARE YOU OK?");

        Map<String, Integer> actual = wordRanking.rankWordsFromTexts(1);

        assertThat(actual.keySet(), hasItem("are"));
        assertThat(actual.keySet(), hasItem("you"));
        assertThat(actual.keySet(), hasItem("ok"));
    }
}
