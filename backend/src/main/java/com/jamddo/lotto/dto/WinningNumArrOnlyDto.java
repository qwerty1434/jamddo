package com.jamddo.lotto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class WinningNumArrOnlyDto {
    private int[] winningNumArr = new int[6];

    public WinningNumArrOnlyDto(int[] winningNumArr) {
        this.winningNumArr = winningNumArr;
    }
}
