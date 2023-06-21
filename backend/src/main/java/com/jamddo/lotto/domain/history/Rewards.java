package com.jamddo.lotto.domain.history;



public class Rewards {
    long[] rewards = new long[5];

    public Rewards(long first, long second, long third, long fourth, long fifth) {
        rewards[0] = first;
        rewards[1] = second;
        rewards[2] = third;
        rewards[3] = fourth;
        rewards[4] = fifth;
    }

    public static Rewards of(long first, long second, long third, long fourth, long fifth){
        return new Rewards(first,second,third,fourth,fifth);
    }

    public long[] getRewards() {
        return rewards;
    }

    public long getRewards(int rank){
        return rewards[rank-1];
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
