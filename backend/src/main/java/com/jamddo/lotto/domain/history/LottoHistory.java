package com.jamddo.lotto.domain.history;

import com.jamddo.lotto.domain.LottoNumber;
import com.jamddo.lotto.domain.LottoNumbers;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

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
    @Embedded
    Rewards rewards;

    // 당첨자 수
    @Embedded
    Beneficiaries beneficiaries;

    // 당첨번호
    @Embedded
    private LottoNumbers lottoNumbers;

    // 보너스 번호
    @Embedded
    private LottoNumber bonusNumber;


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
        return rewards;
    }

    public Beneficiaries getBeneficiaries() {
        return beneficiaries;
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
