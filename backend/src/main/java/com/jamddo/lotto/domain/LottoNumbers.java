package com.jamddo.lotto.domain;

import java.util.List;

public class LottoNumbers {
    private static final int LOTTO_NUMBER_CNT = 6;
    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers() {
        this.lottoNumbers = LottoNumber.getLottoNumbers(LOTTO_NUMBER_CNT);
    }

    public int getMatchCnt(LottoNumbers winningNumbers){
        // 원본값 자체가 변경됨. 원본값을 보존해야 할 이유가 있는가?
        lottoNumbers.retainAll(winningNumbers.lottoNumbers);
        return lottoNumbers.size();
    }

    public boolean containsBonusNumber(LottoNumber bonusNumber){
        return lottoNumbers.contains(bonusNumber);
    }

}
