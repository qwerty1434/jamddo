package com.jamddo.lotto.dto;

import lombok.Builder;

@Builder
public class BuyUntilFirstResponse {
    int cnt;
    long money;
    int[] notFirstButPrize;
    long profit;

}
