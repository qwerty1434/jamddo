package com.jamddo.lotto.domain.winInfo;

import com.jamddo.lotto.domain.lotto.LottoNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class LottoHistoryTest {
    static final LottoHistory lottoHistory = LottoHistory.builder()
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

    @Test
    @DisplayName("당첨금액 정보를 잘 반환하는지 테스트")
    void test1(){
        Rewards rewards = lottoHistory.getRewards();
        assertThat(rewards.getFirst()).isEqualTo(1_000_000_000);
        assertThat(rewards.getSecond()).isEqualTo(100_000_000);
        assertThat(rewards.getThird()).isEqualTo(10_000_000);
        assertThat(rewards.getFourth()).isEqualTo(1_000_000);
        assertThat(rewards.getFifth()).isEqualTo(100_000);
    }

    @Test
    @DisplayName("당첨자 정보를 잘 반환하는지 테스트")
    void test2(){
        Beneficiaries beneficiaries = lottoHistory.getBeneficiaries();
        assertThat(beneficiaries.getFirst()).isEqualTo(1);
        assertThat(beneficiaries.getSecond()).isEqualTo(10);
        assertThat(beneficiaries.getThird()).isEqualTo(100);
        assertThat(beneficiaries.getFourth()).isEqualTo(1_000);
        assertThat(beneficiaries.getFifth()).isEqualTo(10_000);
    }

    @Test
    @DisplayName("당첨 번호를 잘 반환하는지 테스트")
    void test3(){
        LottoNumbers lottoNumbers = lottoHistory.getLottoNumbers();
        assertThat(lottoNumbers.getLottoNumbers().get(0).getLottoNumber()).isEqualTo(2);
        assertThat(lottoNumbers.getLottoNumbers().get(1).getLottoNumber()).isEqualTo(4);
        assertThat(lottoNumbers.getLottoNumbers().get(2).getLottoNumber()).isEqualTo(6);
        assertThat(lottoNumbers.getLottoNumbers().get(3).getLottoNumber()).isEqualTo(8);
        assertThat(lottoNumbers.getLottoNumbers().get(4).getLottoNumber()).isEqualTo(10);
        assertThat(lottoNumbers.getLottoNumbers().get(5).getLottoNumber()).isEqualTo(12);
    }

    @Test
    @DisplayName("보너스 번호를 잘 반환하는지 테스트")
    void test4(){
        int bonusNumber = lottoHistory.getBonusNumber().getLottoNumber();
        assertThat(bonusNumber).isEqualTo(14);
    }

}