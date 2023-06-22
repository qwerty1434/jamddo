package com.jamddo.lotto.domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.jamddo.global.exception.ErrorCode.NOT_VALID_LOTTO_NUM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {
    private static final int START_NUM = 1;
    private static final int END_NUM = 45;

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 12, 27, 33, 43, 21, 38, 2, 16, 19, 39, 41, 45})
    @DisplayName("유효한 숫자가 주어진 경우 로또 번호 객체를 성공적으로 생성")
    void createValidLottoNumber(int givenNumber){
        LottoNumber lottoNumber = LottoNumber.from(givenNumber);
        assertThat(lottoNumber.getLottoNumber()).isBetween(START_NUM,END_NUM);
    }

    @ParameterizedTest
    @ValueSource(ints = {-3, 46, 55})
    @DisplayName("유효한 숫자가 주어진 않으면 로또 번호 객체 생성 실패")
    void createInvalidLottoNumber(int givenNumber){
        assertThatThrownBy(() -> {
            LottoNumber lottoNumber = LottoNumber.from(givenNumber);
        })
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(NOT_VALID_LOTTO_NUM.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,4,6,8})
    @DisplayName("N개의 중복되지 않는 로또번호를 성공적으로 생성")
    void createValidLottoNumbers(int cnt){
        List<LottoNumber> lottoNumbers = LottoNumber.getLottoNumbersAutomatically(cnt);
        Set<LottoNumber> set = new HashSet<>(lottoNumbers);
        assertThat(lottoNumbers.size()).isEqualTo(set.size());
    }

}