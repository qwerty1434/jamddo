package com.jamddo.lotto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BuyUtilFirstPlaceDto {
    int cnt;
    long money;
    int[] notFirstButPrize;
    long profit;
}
