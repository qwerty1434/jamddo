package com.jamddo.lotto.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class WinNum {
    // 회차
    @Id
    @Column(name="lotto_round")
    private Long id;

    @OneToOne(mappedBy = "winNum")
    private WinInfo winInfo;


    // 로또번호
    private int firstNum;
    private int secondNum;
    private int thirdNum;
    private int fourthNum;
    private int fifthNum;
    private int sixthNum;
    private int bonusNum;

}
