package com.jamddo.lotto.service;

import com.jamddo.lotto.dto.*;
import com.jamddo.lotto.repository.WinInfoRepository;
import com.jamddo.lotto.utils.Lotto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WinInfoService {
    private final Lotto lotto;
    private final WinInfoRepository winInfoRepository;

    @Transactional
    public LottoDto buy(){
        // 로또 하나 구매하기
        return lotto.Generate();
    }

    public BuyResultDto scoring(LottoDto myLotto, WinInfoDto winInfoDto){
        return lotto.Scoring(myLotto,winInfoDto);
    }

    public BuyResultDto buyOne(){
        LottoDto myLotto = buy();
        WinInfoDto winInfoDto = winInfoRepository.InfoOfThisWeek();
        return scoring(myLotto,winInfoDto);
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
