package com.jamddo.lotto.domain;

import com.jamddo.lotto.domain.winInfo.Rewards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {
    @Test
    @DisplayName("2등 당첨 테스트")
    void secondRankTest() {
        int matchCnt = 5;
        boolean isBonus = true;
        Rewards rewards = Rewards.of(10_000,1_000,100,10,1);

        Rank rank = Rank.of(matchCnt,isBonus,rewards);

        assertThat(rank.getRank()).isEqualTo(2);
    }

    @Test
    @DisplayName("3등 당첨 테스트")
    void thirdRankTest() {
        int matchCnt = 5;
        boolean isBonus = false;
        Rewards rewards = Rewards.of(10_000,1_000,100,10,1);

        Rank rank = Rank.of(matchCnt,isBonus,rewards);

        assertThat(rank.getRank()).isEqualTo(3);

    }

}