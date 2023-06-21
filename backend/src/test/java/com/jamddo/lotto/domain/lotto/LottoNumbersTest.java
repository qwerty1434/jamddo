package com.jamddo.lotto.domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.jamddo.global.exception.ErrorCode.NOT_VALID_LOTTO_NUM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumbersTest {
    private static final int START_NUM = 1;
    private static final int END_NUM = 45;
    private static final int NUM_CNT = 6;

    @Test
    @DisplayName("6개의 로또번호를 자동으로 생성")
    void createNumbersAutomatically(){
        LottoNumbers lottoNumbers = LottoNumbers.ofAuto();
        assertThat(lottoNumbers.getLottoNumbers().size()).isEqualTo(NUM_CNT);
        lottoNumbers.getLottoNumbersWithInteger()
                .stream()
                .forEach(number -> assertThat(number).isBetween(START_NUM,END_NUM));
    }

    @Test
    @DisplayName("6개의 로또번호를 입력해 로또를 생성")
    void createNumbersManuallyWithValidNum(){
        List<Integer> givenNumbers = List.of(2,4,6,7,10,12);
        LottoNumbers lottoNumbers = LottoNumbers.ofManual(givenNumbers);
        assertThat(lottoNumbers.getLottoNumbers().size()).isEqualTo(NUM_CNT);
        lottoNumbers.getLottoNumbersWithInteger()
                .stream()
                .forEach(number -> assertThat(number).isBetween(START_NUM,END_NUM));
    }

    @Test
    @DisplayName("사용자 입력 중 유효하지 않은 값이 들어오는 경우 예외 발생")
    void createNumbersManuallyWithInvalidNums(){
        List<Integer> givenNumbers = List.of(2,4,6,7,10,88);
        assertThatThrownBy(() -> {
            LottoNumbers lottoNumbers = LottoNumbers.ofManual(givenNumbers);
        })
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(NOT_VALID_LOTTO_NUM.getMessage());
    }

    @Test
    @DisplayName("당첨 수 확인 검증")
    void testMatchCntIsValid(){
        List<Integer> givenNumbers = List.of(2,4,6,7,10,12);
        LottoNumbers lottoNumbers = LottoNumbers.ofManual(givenNumbers);
        LottoNumbers winningNumbers = LottoNumbers.ofManual(givenNumbers);
        assertThat(lottoNumbers.getMatchCnt(winningNumbers)).isEqualTo(NUM_CNT);
    }

}