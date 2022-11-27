package com.jamddo.lotto.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class WinInfo {
    // 회차
    @Id
    @Column(name="lotto_round")
    private Long id;

    // 당첨일자
    LocalDate date;

    @OneToOne
    @JoinColumn(name = "winNum_id")
    private WinNum winNum;

    // 상금
    private long firstPrize;
    private long secondPrize;
    private long thirdPrize;
    private long fourthPrize;

    // 당첨자 수
    private int firstPrizeBeneficiaryNum;
    private int secondPrizeBeneficiaryNum;
    private int thirdPrizeBeneficiaryNum;
    private int fourthPrizeBeneficiaryNum;

}
