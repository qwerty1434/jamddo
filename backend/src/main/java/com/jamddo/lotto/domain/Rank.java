package com.jamddo.lotto.domain;

import com.jamddo.lotto.domain.winInfo.Rewards;

public class Rank {
    private int rank;
    private long prize;

    private Rank(int matchCnt, boolean isBonus, Rewards rewards) {
        this.rank = calculateRank(matchCnt,isBonus);
        this.prize = calculatePrize(rank,rewards);
    }

    public static Rank of(int matchCnt, boolean isBonus, Rewards rewards){
        return new Rank(matchCnt,isBonus,rewards);
    }

    private int calculateRank(int matchCnt, boolean isBonus) {
        if (matchCnt == 6) return 1;
        if (matchCnt == 5) {
            if (isBonus) return 2;
            return 3;
        }
        if (matchCnt == 4) return 4;
        if (matchCnt == 3) return 5;
        return 0;
    }

    private long calculatePrize(int rank, Rewards rewards) {
        return rewards.getRewards(rank);
    }

    public int getRank() {
        return rank;
    }

    public long getPrize() {
        return prize;
    }
}
