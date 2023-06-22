package com.jamddo.lotto.domain.lotto;

import com.jamddo.lotto.domain.winInfo.LottoHistory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {

    private static final int START_NUM = 1;
    private static final int END_NUM = 45;
    private static final int NUM_CNT = 6;

    @Test
    @DisplayName("로또를 자동으로 생성")
    void createLottoAutomatically(){
        Lotto lotto = Lotto.ofAuto();
        LottoNumbers lottoNumbers = lotto.getLottoNumbers();
        assertThat(lottoNumbers.getLottoNumbers().size()).isEqualTo(NUM_CNT);
        lottoNumbers.getLottoNumbersWithInteger()
                .stream()
                .forEach(number -> assertThat(number).isBetween(START_NUM,END_NUM));
    }

    @Test
    @DisplayName("6개의 로또번호를 입력해 생성")
    void createLottoManually(){
        List<Integer> givenNumbers = List.of(2,4,6,7,10,12);
        Lotto lotto = Lotto.ofManual(givenNumbers);
        LottoNumbers lottoNumbers = lotto.getLottoNumbers();
        assertThat(lottoNumbers.getLottoNumbers().size()).isEqualTo(NUM_CNT);
        lottoNumbers.getLottoNumbersWithInteger()
                .stream()
                .forEach(number -> assertThat(number).isBetween(START_NUM,END_NUM));
    }

    @Test
    @DisplayName("순위를 올바르게 계산하는지 확인")
    void testCalculatingRankWorksWell(){
        LottoHistory lottoHistory = LottoHistory.builder()
                .round(1)
                .date(LocalDate.now())
                .firstPrize(1_000_000_000)
                .secondPrize(100_000_000)
                .thirdPrize(10_000_000)
                .fourthPrize(1_000_000)
                .fifthPrize(100_000)
                .firstBeneficiaries(1)
                .secondBeneficiaries(10)
                .thirdBeneficiaries(100)
                .fourthBeneficiaries(1_000)
                .fifthBeneficiaries(10_000)
                .firstNum(2)
                .secondNum(4)
                .thirdNum(6)
                .fourthNum(8)
                .fifthNum(10)
                .sixthNum(12)
                .bonusNum(14)
                .build();
        List<Integer> givenNumbers = List.of(2,4,6,8,10,14);
        Lotto lotto = Lotto.ofManual(givenNumbers);
        assertThat(lotto.getRank(lottoHistory).getRank()).isEqualTo(2);
    }

}