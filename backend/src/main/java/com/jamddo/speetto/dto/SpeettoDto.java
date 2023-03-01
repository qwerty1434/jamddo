package com.jamddo.speetto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SpeettoDto {
    private int round;
    private int remainCntOfFirstTicket;
    private int remainCntOfSecondTicket;
    private int remainCntOfThirdTicket;
    private int remainCntOfFourthTicket;
    private int remainCntOfFifthTicket;
    private int remainCntOfLosingTicket;

    public int totalRemainCnt(){
        return remainCntOfFirstTicket + remainCntOfSecondTicket + remainCntOfThirdTicket + remainCntOfFourthTicket + remainCntOfFifthTicket  + remainCntOfLosingTicket;
    }
}
