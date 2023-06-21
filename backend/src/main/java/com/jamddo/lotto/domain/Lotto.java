package com.jamddo.lotto.domain;

import com.jamddo.lotto.domain.history.LottoHistory;

public class Lotto {
    long round;
    private final LottoNumbers lottoNumbers;

    public Lotto() {
        this.round = generateRound();
        this.lottoNumbers = new LottoNumbers();
    }

    /*
     *  첫 로또 추첨일은 2002년 12월 7일
     *  첫 로또 구매 가능일은 2001년 11월 30일
     *  1_038_654_000_000L은 2001년 11월 30일을 milliseconds로 환산한 값
     */
    private long generateRound(){
        final long ONE_WEEK = 1000 * 60 * 60 * 24 * 7;
        final long START_DATE = 1_038_654_000_000L;
        final long NOW_DATE = System.currentTimeMillis();
        return ((NOW_DATE - START_DATE) / ONE_WEEK +
                (((NOW_DATE - START_DATE) % ONE_WEEK == 0) ? 0 : 1));
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


    public long getRound() {
        return round;
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }
}
