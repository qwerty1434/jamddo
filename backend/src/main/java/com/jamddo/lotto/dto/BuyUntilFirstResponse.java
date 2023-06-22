package com.jamddo.lotto.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BuyUntilFirstResponse {
    int cnt;
    long money;
    int[] notFirstButPrize;
    long profit;

}
