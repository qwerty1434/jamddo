package com.jamddo.lotto.service;

import com.jamddo.lotto.dto.*;
import com.jamddo.lotto.repository.WinInfoRepository;
import com.jamddo.lotto.utils.Lotto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SimulationService {
    private final Lotto lotto;
    private final WinInfoRepository winInfoRepository;

    @Transactional
    public LottoDto buy(){
        return lotto.Generate();
    }
    @Transactional
    public BuyResultDto scoring(LottoDto myLotto, WinInfoDto winInfoDto){
        return lotto.Scoring(myLotto,winInfoDto);
    }
    @Transactional
    public BuyResultDto buyOne(){
        LottoDto myLotto = buy();
        WinInfoDto winInfoDto = winInfoRepository.InfoOfThisWeek();
        return scoring(myLotto,winInfoDto);
    }

    @Transactional
    public List<BuyResultDto> buyBundle(int Cnt){
        WinInfoDto winInfoDto = winInfoRepository.InfoOfThisWeek();
        List<BuyResultDto> result = new ArrayList<>();
        for (int i = 0; i < Cnt; i++) {
            LottoDto myLotto = buy();
            result.add(scoring(myLotto,winInfoDto));
        }
        Collections.sort(result, (o1, o2) -> {
            if(o1.getRank() == -1 || o2.getRank() == -1) return o2.getRank() - o1.getRank();
            return o1.getRank() - o2.getRank();
        });



        return result;

    }

    @Transactional
    public BuyUtilFirstPlaceDto untilFirstPlace(){
        int cnt = 0;
        long money = 0;
        int[] notFirstButPrize = new int[4];
        WinInfoDto winInfoDto = winInfoRepository.InfoOfThisWeek();
        while(true){
            cnt++;
            money+=1000;
            LottoDto myLotto = buy();
            BuyResultDto result = scoring(myLotto,winInfoDto);
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

        // 얼마의 이득 혹은 손해를 봤는지
        long profit =
                winInfoDto.getFirstPrize() / winInfoDto.getFirstPrizeBeneficiaryNum()
                + (notFirstButPrize[0] *( winInfoDto.getSecondPrize()) / winInfoDto.getSecondPrizeBeneficiaryNum())
                + (notFirstButPrize[1] *( winInfoDto.getThirdNum()) / winInfoDto.getThirdPrizeBeneficiaryNum())
                + (notFirstButPrize[2] *( winInfoDto.getFourthNum()) / winInfoDto.getFourthPrizeBeneficiaryNum())
                + notFirstButPrize[3] *(5000)
                - money;


        return BuyUtilFirstPlaceDto.builder()
                .cnt(cnt)
                .money(money)
                .notFirstButPrize(notFirstButPrize)
                .profit(profit)
                .build();
    }
}
