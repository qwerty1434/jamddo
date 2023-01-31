package com.jamddo.lotto.dto;

import com.jamddo.lotto.domain.WinNum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WinningNumDto {
    private long round;
    private int firstNum;
    private int secondNum;
    private int thirdNum;
    private int fourthNum;
    private int fifthNum;
    private int sixthNum;
    private int bonusNum;

    public WinNum dtoToEntity(){
        return WinNum.builder()
                .id(this.round)
                .firstNum(this.firstNum)
                .secondNum(this.secondNum)
                .thirdNum(this.thirdNum)
                .fourthNum(this.fourthNum)
                .fifthNum(this.fifthNum)
                .sixthNum(this.sixthNum)
                .bonusNum(this.bonusNum)
                .build();
    }
}
