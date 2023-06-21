package com.jamddo.lotto.dto;

import com.jamddo.lotto.domain.LottoNumber;
import com.jamddo.lotto.domain.LottoNumbers;
import lombok.Getter;

@Getter
public class WinningNumResponse {
    LottoNumbers lottoNumbers;
    LottoNumber bonusNumber;

    private WinningNumResponse(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumResponse of(LottoNumbers lottoNumbers, LottoNumber bonusNumber){
        return new WinningNumResponse(lottoNumbers,bonusNumber);
    }

}
