package com.jamddo.lotto.domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {
    private static final int LOTTO_NUMBER_CNT = 6;
    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        Collections.sort(lottoNumbers,Comparator.comparingInt(LottoNumber::getLottoNumber));
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
        int myNumberIdx = 0;
        int winningNumberIdx = 0;
        int duplicateCnt = 0;
        while(myNumberIdx < lottoNumbers.size() && winningNumberIdx < lottoNumbers.size()){
            if(lottoNumbers.get(myNumberIdx).getLottoNumber() == winningNumbers.lottoNumbers.get(winningNumberIdx).getLottoNumber()){
                duplicateCnt++;
                myNumberIdx++;
            }else if(lottoNumbers.get(myNumberIdx).getLottoNumber() > winningNumbers.lottoNumbers.get(winningNumberIdx).getLottoNumber()){
                winningNumberIdx++;
            }else {
                myNumberIdx++;
            }
        }
        return duplicateCnt;
    }

    public boolean isContainBonusNumber(LottoNumber bonusNumber){
        return lottoNumbers.contains(bonusNumber);
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public List<Integer> getLottoNumbersWithInteger(){
        return lottoNumbers.stream()
                .map(LottoNumber::getLottoNumber)
                .sorted()
                .collect(Collectors.toList());
    }
}
