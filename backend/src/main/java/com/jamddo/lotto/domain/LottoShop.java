package com.jamddo.lotto.domain;

import com.jamddo.lotto.domain.history.LottoHistory;
import com.jamddo.lotto.domain.history.Rewards;
import com.jamddo.lotto.dto.BuyUntilFirstResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LottoShop {

    public static Lotto buyOne(){
        return Lotto.ofAuto();
    }

    public static List<Lotto> buyBundle(int cnt){
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < cnt; i++){
            lottos.add(Lotto.ofAuto());
        }
        return lottos;
    }

    public static BuyUntilFirstResponse buyUntilFirstPlace(LottoHistory lottoHistory){
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

        return BuyUntilFirstResponse.builder()
                .cnt(cnt)
                .money(money)
                .notFirstButPrize(notFirstButPrize)
                .profit(profit)
                .build();
    }

    private static long calculateProfit(long money, Rewards rewards, int[] notFirstButPrize){
        return  rewards.getFirst()
                + (notFirstButPrize[0] * rewards.getSecond())
                + (notFirstButPrize[1] * rewards.getThird())
                + (notFirstButPrize[2] * rewards.getFourth())
                + (notFirstButPrize[3] * rewards.getFifth())
                - money;
    }

}
