package com.jamddo.lotto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BuyResultDto {
    private int rank;
    private long winningPrize;
    private int beneficiaryNum;
    private long myPrize;
    private LottoDto lottoDto;
}
