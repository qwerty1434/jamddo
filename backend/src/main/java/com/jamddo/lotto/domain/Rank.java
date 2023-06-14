package com.jamddo.lotto.domain;

import java.util.List;

public class Rank {

    private int matchCnt;
    private int prize;
    private int beneficiaryNum;
    private int round;
    static{

    }

    public Rank(int matchCnt, int prize, int beneficiaryNum) {
        this.matchCnt = matchCnt;
        this.prize = prize;
        this.beneficiaryNum = beneficiaryNum;
    }

    public boolean isUpdated(int round){
        return this.round == round;
    }
}
