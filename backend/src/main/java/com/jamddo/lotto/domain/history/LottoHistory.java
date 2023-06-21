package com.jamddo.lotto.domain.history;

import com.jamddo.lotto.domain.LottoNumber;
import com.jamddo.lotto.domain.LottoNumbers;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class LottoHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 회차
    int round;
    // 당첨일자
    @DateTimeFormat(pattern="yyyy.MM.dd")
    private LocalDate date;

    // 상금
    private long firstPrize;
    private long secondPrize;
    private long thirdPrize;
    private long fourthPrize;
    private long fifthPrize;
    // 당첨자 수
    private int firstPrizeBeneficiaryNum;
    private int secondPrizeBeneficiaryNum;
    private int thirdPrizeBeneficiaryNum;
    private int fourthPrizeBeneficiaryNum;
    private int fifthPrizeBeneficiaryNum;
    // 당첨 번호
    private int firstNum;
    private int secondNum;
    private int thirdNum;
    private int fourthNum;
    private int fifthNum;
    private int sixthNum;
    private int bonusNum;


    public Long getId() {
        return id;
    }

    public int getRound() {
        return round;
    }

    public LocalDate getDate() {
        return date;
    }

    public Rewards getRewards() {
        return Rewards.of(firstPrize,secondPrize,thirdPrize,fourthPrize,fifthPrize);
    }

    public Beneficiaries getBeneficiaries() {
        return Beneficiaries.of(firstPrizeBeneficiaryNum,secondPrizeBeneficiaryNum,thirdPrizeBeneficiaryNum,fourthPrizeBeneficiaryNum,fifthPrizeBeneficiaryNum);
    }

    public LottoNumbers getLottoNumbers() {
        List<Integer> numbers = List.of(firstNum,secondNum,thirdNum,fourthNum,fifthNum,sixthNum);
        return LottoNumbers.ofManual(numbers);
    }

    public LottoNumber getBonusNumber() {
        return LottoNumber.from(bonusNum);
    }
}
