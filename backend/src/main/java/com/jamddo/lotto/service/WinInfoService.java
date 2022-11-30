package com.jamddo.lotto.service;

import com.jamddo.lotto.dto.ColorStatisticDto;
import com.jamddo.lotto.dto.NumStatisticDto;
import com.jamddo.lotto.dto.WinInfoDto;
import com.jamddo.lotto.dto.WinningNumDto;
import com.jamddo.lotto.repository.WinInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WinInfoService {
    private final WinInfoRepository winInfoRepository;

    @Transactional
    public WinningNumDto winningNumOfThisWeek() {
        return winInfoRepository.winningNumOfThisWeek();
    }

    @Transactional
    public WinInfoDto infoOfThisWeek(){
        return winInfoRepository.InfoOfThisWeek();
    }

    @Transactional
    public NumStatisticDto NumStatistic() {
        return winInfoRepository.NumStatistic();
    }

    @Transactional
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

}
