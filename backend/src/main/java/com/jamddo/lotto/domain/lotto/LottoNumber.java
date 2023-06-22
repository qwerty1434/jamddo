package com.jamddo.lotto.domain.lotto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.jamddo.global.exception.ErrorCode.NOT_VALID_LOTTO_NUM;

public class LottoNumber {
    private static final int START_NUM = 1;
    private static final int END_NUM = 45;
    private static final List<LottoNumber> LOTTO_NUMBER_LIST;
    private static final Map<Integer,LottoNumber> LOTTO_NUMBER_MAP = new HashMap<>();
    private final int lottoNumber;

    static{
        LOTTO_NUMBER_LIST = IntStream.rangeClosed(START_NUM,END_NUM)
                .boxed()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        LOTTO_NUMBER_LIST.stream()
                .forEach(LottoNumber -> LOTTO_NUMBER_MAP.put(LottoNumber.lottoNumber,LottoNumber));
    }

    private LottoNumber(int lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static LottoNumber from(int givenNumber){
        if(START_NUM <= givenNumber && givenNumber <= END_NUM) return LOTTO_NUMBER_MAP.get(givenNumber);
        throw new IllegalArgumentException(NOT_VALID_LOTTO_NUM.getMessage());
    }

    public static List<LottoNumber> getLottoNumbersAutomatically(int cnt){
        Collections.shuffle(LOTTO_NUMBER_LIST);
        return new ArrayList<>(LOTTO_NUMBER_LIST.subList(0,cnt));
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
