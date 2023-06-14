package com.jamddo.lotto.domain;

public class Lotto {
    private final LottoNumbers lottoNumbers;

    public Lotto() {
        this.lottoNumbers = new LottoNumbers();
    }

    public int getMatchCnt(LottoNumbers winningNumbers){
        return lottoNumbers.getMatchCnt(winningNumbers);
    }

    public boolean containsBonusNumber(LottoNumber bonusNumber){
        return lottoNumbers.containsBonusNumber(bonusNumber);
    }


}
