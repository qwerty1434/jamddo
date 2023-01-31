package com.jamddo.lotto.service;

import com.jamddo.lotto.dto.BuyResultDto;
import com.jamddo.lotto.dto.BuyUtilFirstPlaceDto;
import com.jamddo.lotto.dto.LottoDto;
import com.jamddo.lotto.repository.WinInfoRepository;
import com.jamddo.lotto.utils.Lotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class SimulationServiceTest {
    @Autowired
    SimulationService simulationService;

    @Test
    @WithAnonymousUser
    public void 한개_구매(){
        BuyResultDto result = simulationService.buyOne();
        Assertions.assertThatNoException();
    }

    @Test
    public void Cnt개_구매(){
        int Cnt = 10;
        List<BuyResultDto> result = simulationService.buyBundle(Cnt);
        Assertions.assertThatNoException();
    }

    @Test
    public void 우승할때까지_구매(){
        BuyUtilFirstPlaceDto result = simulationService.untilFirstPlace();
        Assertions.assertThatNoException();
    }

}