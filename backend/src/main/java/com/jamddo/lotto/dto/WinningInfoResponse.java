package com.jamddo.lotto.dto;

import com.jamddo.lotto.domain.history.Beneficiaries;
import com.jamddo.lotto.domain.history.Rewards;

public class WinningInfoResponse {
    int round;
    Rewards rewards;
    Beneficiaries beneficiaries;

    private WinningInfoResponse(int round, Rewards rewards, Beneficiaries beneficiaries) {
        this.round = round;
        this.rewards = rewards;
        this.beneficiaries = beneficiaries;
    }

    public static WinningInfoResponse of(int round, Rewards rewards, Beneficiaries beneficiaries){
        return new WinningInfoResponse(round,rewards,beneficiaries);
    }

    public int getRound() {
        return round;
    }

    public long[] getRewards() {
        return rewards.getRewards();
    }

    public int[] getBeneficiaries() {
        return beneficiaries.getBeneficiaries();
    }
}
