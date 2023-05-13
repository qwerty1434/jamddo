package com.jamddo.lotto.utils;

import com.jamddo.lotto.dto.LottoDto;
import com.jamddo.lotto.service.SimulationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LottoTest {
    @Autowired
    SimulationService simulationService;

    @Test
    @DisplayName("구매한 번호에는 중복이 발생하면 안됩니다.")
    public void buyNotDuplicateTest(){
        LottoDto lottoDto = simulationService.buyOneLotto();
        List<Integer> lst = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        lst.add(lottoDto.getFirstNum());
        lst.add(lottoDto.getSecondNum());
        lst.add(lottoDto.getThirdNum());
        lst.add(lottoDto.getFourthNum());
        lst.add(lottoDto.getFifthNum());
        lst.add(lottoDto.getSixthNum());

        set.add(lottoDto.getFirstNum());
        set.add(lottoDto.getSecondNum());
        set.add(lottoDto.getThirdNum());
        set.add(lottoDto.getFourthNum());
        set.add(lottoDto.getFifthNum());
        set.add(lottoDto.getSixthNum());
        Assertions.assertEquals(lst.size(),set.size());
    }


}