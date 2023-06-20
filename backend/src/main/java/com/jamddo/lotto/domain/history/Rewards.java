package com.jamddo.lotto.domain.history;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Rewards {
    List<Long> rewards = new ArrayList<>();

    public List<Long> getRewards() {
        return rewards;
    }

    public long getFirstReward(){
        return rewards.get(0);
    }
    public long getSecondReward(){
        return rewards.get(1);
    }
    public long getThirdReward(){
        return rewards.get(2);
    }
    public long getFourthReward(){
        return rewards.get(3);
    }
    public long getFifthReward(){
        return rewards.get(4);
    }
}
