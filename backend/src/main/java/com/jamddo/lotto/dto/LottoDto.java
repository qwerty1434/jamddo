package com.jamddo.lotto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LottoDto {
    private int firstNum;
    private int secondNum;
    private int thirdNum;
    private int fourthNum;
    private int fifthNum;
    private int sixthNum;

    private final int[] numList = new int[]{this.firstNum,this.secondNum,this.thirdNum,this.fourthNum,this.fifthNum,this.sixthNum };
}
