package com.jamddo.lotto.domain;

import javax.persistence.ElementCollection;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {
    private static final int LOTTO_NUMBER_CNT = 6;
    @ElementCollection
    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoNumbers ofAuto(){
        List<LottoNumber> lottoNumbers = LottoNumber.getLottoNumbersAutomatically(LOTTO_NUMBER_CNT);
        return new LottoNumbers(lottoNumbers);
    }
    public static LottoNumbers ofManual(List<Integer> givenNumbers){
        List<LottoNumber> lottoNumbers = givenNumbers.stream()
                .map(givenNumber -> LottoNumber.from(givenNumber))
                .collect(Collectors.toList());
        return new LottoNumbers(lottoNumbers);
    }
    public int getMatchCnt(LottoNumbers winningNumbers){
        // 원본값 자체가 변경됨. 원본값을 보존해야 할 이유가 있는가?
        lottoNumbers.retainAll(winningNumbers.lottoNumbers);
        return lottoNumbers.size();
    }

    public boolean isContainBonusNumber(LottoNumber bonusNumber){
        return lottoNumbers.contains(bonusNumber);
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
