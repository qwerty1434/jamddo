package com.jamddo.lotto.domain;

import com.jamddo.lotto.domain.history.LottoHistory;
import com.jamddo.lotto.domain.history.Rewards;
import com.jamddo.lotto.dto.BuyUntilFirstPlaceDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LottoShop {

    public static Lotto buyOne(){
        return new Lotto();
    }

    public static List<Lotto> buyBundle(int cnt){
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < cnt; i++){
            lottos.add(new Lotto());
        }
        return lottos;
    }

    public static BuyUntilFirstPlaceDto untilFirstPlace(LottoHistory lottoHistory){
        int cnt = 0;
        long money = 0;
        int[] notFirstButPrize = new int[4];
        while(true){
            cnt++;
            money+=1000;
            Lotto myLotto = buyOne();
            Rank result = myLotto.getRank(lottoHistory);

            if(result.getRank() == 1) break;
            else if(result.getRank() == 2) {
                notFirstButPrize[0]++;
            }
            else if(result.getRank() == 3) {
                notFirstButPrize[1]++;
            }
            else if(result.getRank() == 4) {
                notFirstButPrize[2]++;
            }
            else if(result.getRank() == 5) {
                notFirstButPrize[3]++;
            }
        }
        Rewards rewards = lottoHistory.getRewards();

        long profit = calculateProfit(money,rewards,notFirstButPrize);

        return BuyUntilFirstPlaceDto.builder()
                .cnt(cnt)
                .money(money)
                .notFirstButPrize(notFirstButPrize)
                .profit(profit)
                .build();
    }

    private static long calculateProfit(long money, Rewards rewards, int[] notFirstButPrize){
        return  rewards.getFirstReward()
                + (notFirstButPrize[0] * rewards.getSecondReward())
                + (notFirstButPrize[1] * rewards.getThirdReward())
                + (notFirstButPrize[2] * rewards.getFourthReward())
                + (notFirstButPrize[3] * rewards.getFifthReward())
                - money;
    }

}
