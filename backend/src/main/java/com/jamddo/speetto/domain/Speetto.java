package com.jamddo.speetto.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@Entity
@Getter
public class Speetto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="speetto_id")
    private Long id;

    private int round;
    private int remainCntOfFirstTicket;
    private int remainCntOfSecondTicket;
    private int remainCntOfThirdTicket;
    private int remainCntOfFourthTicket;
    private int remainCntOfFifthTicket;
    private int remainCntOfLosingTicket;


    public Speetto(){
        this.round = 1;
        basicSettingOfTicket();
    }

    public void nextRun(){
        this.round++;
        basicSettingOfTicket();
    }

    public int totalRemainCnt(){
        return remainCntOfFirstTicket + remainCntOfSecondTicket + remainCntOfThirdTicket + remainCntOfFourthTicket + remainCntOfFifthTicket  + remainCntOfLosingTicket;
    }

    private void basicSettingOfTicket(){
        this.remainCntOfFirstTicket = 8;
        this.remainCntOfSecondTicket = 40;
        this.remainCntOfThirdTicket = 220_000;
        this.remainCntOfFourthTicket = 1_000_000;
        this.remainCntOfFifthTicket = 12_000_000;
        this.remainCntOfLosingTicket = 26_779_952;
    }

    public int buyOne(){
        if(totalRemainCnt() == 0) nextRun();
        int rNum = (int)(Math.random() * totalRemainCnt()) +1; // 1~N
        int[] cumSum = new int[6];
        cumSum[0] = this.remainCntOfFirstTicket;
        cumSum[1] = cumSum[0] + this.remainCntOfSecondTicket;
        cumSum[2] = cumSum[1] + this.remainCntOfThirdTicket;
        cumSum[3] = cumSum[2] + this.remainCntOfFourthTicket;
        cumSum[4] = cumSum[3] + this.remainCntOfFifthTicket;
        cumSum[5] = cumSum[4] + this.remainCntOfLosingTicket;

        if(rNum <= cumSum[0]){
            --this.remainCntOfFirstTicket;
            return 1;
        }else if(rNum <= cumSum[1]){
            --this.remainCntOfSecondTicket;
            return 2;
        }else if(rNum <= cumSum[2]){
            --this.remainCntOfThirdTicket;
            return 3;
        }else if(rNum <= cumSum[3]){
            --this.remainCntOfFourthTicket;
            return 4;
        }else if(rNum <= cumSum[4]){
            --this.remainCntOfFifthTicket;
            return 5;
        }else{
            --this.remainCntOfLosingTicket;
            return -1;
        }
    }

}
