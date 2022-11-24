package com.jamddo.lotto.utils;

import com.jamddo.lotto.dto.LottoDto;
import com.jamddo.lotto.dto.ResultDto;
import com.jamddo.lotto.dto.WinInfoDto;
import com.jamddo.lotto.dto.WinningNumsDto;
import com.jamddo.lotto.repository.WinInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.Random;



@RequiredArgsConstructor
@Component
public class Lotto {
    private final WinInfoRepository winInfoRepository;


    public LottoDto Generate(){
        Random random = new Random();
        int[] Lotto = new int[6];
        for (int i = 0; i < 6; i++) {
            int num = (int)(Math.random() * 45) + 1;
            for (int j = 0; j < i; j++) {
                if(Lotto[i] == Lotto[j]){
                    i--;
                    break;
                }
            }
        }
        Arrays.sort(Lotto);
        return LottoDto.builder()
                .firstNum(Lotto[0])
                .secondNum(Lotto[1])
                .thirdNum(Lotto[2])
                .fourthNum(Lotto[3])
                .fifthNum(Lotto[4])
                .sixthNum(Lotto[5])
                .build();
    }
    public ResultDto Scoring(LottoDto lotto){
        WinningNumsDto winningNumsDto = winInfoRepository.winningNumOfThisWeek();
        WinInfoDto winInfoDto = winInfoRepository.InfoOfThisWeek();
        boolean bonus = false;
        int cnt = 0;
        for (int i:lotto.getNumList()) {
            for (int j:winningNumsDto.getNumList()) {
                if(i == j) cnt++;
            }
            if(i == winningNumsDto.getBonusNum()){
                bonus = true;
            }
        }
        int rank;
        Long winningPrize;
        int beneficiaryNum;

        if(cnt == 6){
            rank = 1;
            winningPrize = winInfoDto.getFirstPrize();
            beneficiaryNum = winInfoDto.getFirstPrizeBeneficiaryNum();
        }else if(cnt == 5 && bonus){
            rank = 2;
            winningPrize = winInfoDto.getSecondPrize();
            beneficiaryNum = winInfoDto.getSecondPrizeBeneficiaryNum();
        }else if(cnt == 5){
            rank = 3;
            winningPrize = winInfoDto.getThirdPrize();
            beneficiaryNum = winInfoDto.getThirdPrizeBeneficiaryNum();
        }else if(cnt == 4){
            rank = 4;
            winningPrize = winInfoDto.getFourthPrize();
            beneficiaryNum = winInfoDto.getFourthPrizeBeneficiaryNum();
        }else if(cnt == 3){
            rank = 5;
            winningPrize = 5000L;
            beneficiaryNum = 1;
        }else{
            rank = -1;
            winningPrize = 0L;
            beneficiaryNum = 1;
        }




        return ResultDto.builder()
                .rank(rank)
                .winningPrize(winningPrize)
                .beneficiaryNum(beneficiaryNum)
                .myPrize(winningPrize/beneficiaryNum)
                .lottoDto(lotto)
                .build();
    }


}
