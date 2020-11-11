package org.example.wordcounter.core.usecases;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RankingOrderingTests extends WordRankingUseCaseTest {

    @Test
    void singleSimpleText_ShouldRankDescendingOrder() {
        givenTextsWithSameContent(1,"are you you");

        Map<String, Integer> actual = wordRanking.rankWordsFromTexts(1);

        assertKeysInThisOrder(actual, "you", "are");
    }

    @Test
    void twoTexts_ShouldRankWordsInDescendingOrder() {
        givenTexts("you are", "you could");

        Map<String, Integer> actual = wordRanking.rankWordsFromTexts(1);

        String highestRank = actual.keySet().iterator().next();
        assertEquals("you", highestRank);
    }

    @Test
    void twoTextsWithPairOfWords_ShouldRankWordsInDescendingOrder() {
        givenTexts("you are", "you could, you are right");

        Map<String, Integer> actual = wordRanking.rankWordsFromTexts(2);

        String highestRank = actual.keySet().iterator().next();
        assertEquals("you are", highestRank);
    }

    private void assertKeysInThisOrder(Map<String, Integer> map, String... strings) {
        assertThat(map.keySet(), contains(strings));
    }
}
