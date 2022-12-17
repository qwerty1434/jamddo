package com.jamddo.lotto.service;

import com.jamddo.lotto.dto.BuyResultDto;
import com.jamddo.lotto.dto.BuyUtilFirstPlaceDto;
import com.jamddo.lotto.dto.LottoDto;
import com.jamddo.lotto.repository.WinInfoRepository;
import com.jamddo.lotto.utils.Lotto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class SimulationServiceTest {
    @Autowired
    SimulationService simulationService;

    @Test
    public void 한개_구매(){
        BuyResultDto result = simulationService.buyOne();
        System.out.println("result = " + result.toString());
    }

    @Test
    public void Cnt개_구매(){
        int Cnt = 10;
        List<BuyResultDto> result = simulationService.buyBundle(Cnt);
        for (BuyResultDto buyResultDto : result) {
            System.out.println("buyResultDto = " + buyResultDto);
        }
    }

    @Test
    public void 우승할때까지_구매(){
        BuyUtilFirstPlaceDto result = simulationService.untilFirstPlace();
        System.out.println("result = " + result);
    }

}