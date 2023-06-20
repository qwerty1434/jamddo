package com.jamddo.lotto.dto;

import lombok.Builder;

@Builder
public class BuyUntilFirstPlaceDto {
    int cnt;
    long money;
    int[] notFirstButPrize;
    long profit;

    public BuyUntilFirstPlaceDto(int cnt, long money, int[] notFirstButPrize, long profit) {
        this.cnt = cnt;
        this.money = money;
        this.notFirstButPrize = notFirstButPrize;
        this.profit = profit;
    }
}
