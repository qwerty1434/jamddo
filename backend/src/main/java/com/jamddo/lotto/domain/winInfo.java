package com.jamddo.lotto.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class winInfo {
    @Id
    @Column(name="lotto_round")
    private Long id;

    private int firstNum;
    private int secondNum;
    private int thirdNum;
    private int fourthNum;
    private int fifthNum;
    private int sixthNum;
    private int bonusNum;

    private long firstPrize;
    private long secondPrize;
    private long thirdPrize;
    private long fourthPrize;

    private int firstPrizeBeneficiaryNum;
    private int secondPrizeBeneficiaryNum;
    private int thirdPrizeBeneficiaryNum;
    private int fourthPrizeBeneficiaryNum;

}
