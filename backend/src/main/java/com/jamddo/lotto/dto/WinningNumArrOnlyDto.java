package com.jamddo.lotto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WinningNumArrOnlyDto {
    private int[] winningNumArr = new int[6];
}
