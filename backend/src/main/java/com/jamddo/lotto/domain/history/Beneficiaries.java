package com.jamddo.lotto.domain.history;

public class Beneficiaries {
    int[] beneficiaries = new int[5];

    public Beneficiaries(int first, int second, int third, int fourth, int fifth) {
        beneficiaries[0] = first;
        beneficiaries[1] = second;
        beneficiaries[2] = third;
        beneficiaries[3] = fourth;
        beneficiaries[4] = fifth;
    }

    public static Beneficiaries of(int first, int second, int third, int fourth, int fifth){
        return new Beneficiaries(first,second,third,fourth,fifth);
    }

    public int[] getBeneficiaries() {
        return beneficiaries;
    }

    public int getFirst(){
        return beneficiaries[0];
    }
    public int getSecond(){
        return beneficiaries[1];
    }
    public int getThird(){
        return beneficiaries[2];
    }
    public int getFourth(){
        return beneficiaries[3];
    }
    public int getFifth(){
        return beneficiaries[4];
    }

}
