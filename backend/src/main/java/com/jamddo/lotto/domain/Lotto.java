package com.jamddo.lotto.domain;

import com.jamddo.lotto.domain.history.LottoHistory;

public class Lotto {
    int round;
    private final LottoNumbers lottoNumbers;

    public Lotto() {
        this.lottoNumbers = new LottoNumbers();
    }
    public Rank getRank(LottoHistory lottoHistory){
        int matchCnt = getMatchCnt(lottoHistory.getLottoNumbers());
        boolean isBonus = isContainBonusNumber(lottoHistory.getBonusNumber());
        return Rank.of(matchCnt,isBonus,lottoHistory.getRewards());
    }
    public int getMatchCnt(LottoNumbers winningNumbers){
        return lottoNumbers.getMatchCnt(winningNumbers);
    }

    public boolean isContainBonusNumber(LottoNumber bonusNumber){
        return lottoNumbers.isContainBonusNumber(bonusNumber);
    }

    public boolean isUpdated(int round){
        return this.round == round;
    }


    public int getRound() {
        return round;
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }
}
