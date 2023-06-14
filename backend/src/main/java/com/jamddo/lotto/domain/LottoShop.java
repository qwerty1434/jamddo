package com.jamddo.lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LottoShop {

    public static Lotto buyOne(){
        return new Lotto();
    }

    public static List<Lotto> buyBundle(int cnt){
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < cnt; i++){
            lottos.add(new Lotto());
        }
        return lottos;
    }

    public static void buyUntilFirstPlace(){

    }
}
