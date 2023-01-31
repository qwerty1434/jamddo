package com.jamddo.lotto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NumStatisticDto {
    int[] statisticResult = new int[46]; // 인덱스와 숫자번호를 일치시킴
}
