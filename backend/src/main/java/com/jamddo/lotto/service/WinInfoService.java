package com.jamddo.lotto.service;

import com.jamddo.lotto.dto.*;
import com.jamddo.lotto.repository.WinInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WinInfoService {
    private final WinInfoRepository winInfoRepository;

    public WinningNumDto winningNumOfThisWeek() {
        return winInfoRepository.winningNumOfThisWeek();
    }

    public WinInfoDto infoOfThisWeek(){
        return winInfoRepository.InfoOfThisWeek();
    }

    public NumStatisticDto NumStatistic() {
        return winInfoRepository.NumStatistic();
    }

    public ColorStatisticDto ColorStatistic(){
        NumStatisticDto numStatisticDto = winInfoRepository.NumStatistic();
        int[] colorStatistic = new int[5];
        int[] statisticResult = numStatisticDto.getStatisticResult();
        for (int i = 1; i <= 45 ; i++){
            if(i<=10){
                colorStatistic[0] += statisticResult[i];
            }else if(10 < i && i <= 20){
                colorStatistic[1] += statisticResult[i];
            }else if(20 < i && i <= 30){
                colorStatistic[2] += statisticResult[i];
            }else if(30 < i && i <= 40){
                colorStatistic[3] += statisticResult[i];
            }else if(40 < i && i <= 45){
                colorStatistic[4] += statisticResult[i];
            }
        }
        return ColorStatisticDto.builder().colorStatistic(colorStatistic).build();
    }

    public WinningNumArrOnlyDto WinningNumOnly(){
        int[] winningNumArr = new int[6];
        WinningNumDto result = winInfoRepository.winningNumOfThisWeek();
        winningNumArr[0] = result.getFifthNum();
        winningNumArr[1] = result.getSecondNum();
        winningNumArr[2] = result.getThirdNum();
        winningNumArr[3] = result.getFourthNum();
        winningNumArr[4] = result.getFifthNum();
        winningNumArr[5] = result.getSixthNum();
        return WinningNumArrOnlyDto.builder().winningNumArr(winningNumArr).build();
    }
}
