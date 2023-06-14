package com.jamddo.lotto.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {
    private static final List<LottoNumber> LOTTO_NUMBER_LIST;
    private static final Map<Integer,LottoNumber> LOTTO_NUMBER_MAP = new HashMap<>();

    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        this.lottoNumber = lottoNumber;
    }
    static{
        LOTTO_NUMBER_LIST = IntStream.rangeClosed(1,45)
                .boxed()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        LOTTO_NUMBER_LIST.stream()
                .forEach(v -> LOTTO_NUMBER_MAP.put(v.lottoNumber, v));
    }

    public static List<LottoNumber> getLottoNumbers(int cnt){
        Collections.shuffle(LOTTO_NUMBER_LIST);
        return LOTTO_NUMBER_LIST.subList(0,cnt);
    }

    public int getLottoNumber(){
        return lottoNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }
}
