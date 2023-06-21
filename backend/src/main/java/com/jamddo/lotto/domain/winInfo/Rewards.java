package com.jamddo.lotto.domain.winInfo;



public class Rewards {
    long[] rewards = new long[6];

    public Rewards(long first, long second, long third, long fourth, long fifth) {
        rewards[1] = first;
        rewards[2] = second;
        rewards[3] = third;
        rewards[4] = fourth;
        rewards[5] = fifth;
    }

    public static Rewards of(long first, long second, long third, long fourth, long fifth){
        return new Rewards(first,second,third,fourth,fifth);
    }

    public long[] getRewards() {
        return rewards;
    }

    public long getRewards(int rank){
        if(rank <= 0) return rewards[0];
        return rewards[rank];
    }

    public long getFirst(){
        return rewards[0];
    }
    public long getSecond(){
        return rewards[1];
    }
    public long getThird(){
        return rewards[2];
    }
    public long getFourth(){
        return rewards[3];
    }
    public long getFifth(){
        return rewards[4];
    }
}
