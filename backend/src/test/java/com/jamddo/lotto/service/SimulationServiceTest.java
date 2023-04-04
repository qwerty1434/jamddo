package com.jamddo.lotto.service;

import com.jamddo.global.exception.CustomException;
import com.jamddo.lotto.dto.BuyResultDto;
import com.jamddo.lotto.dto.BuyUtilFirstPlaceDto;
import com.jamddo.lotto.dto.WinInfoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
class SimulationServiceTest {
    @Autowired
    SimulationService simulationService;

    @Autowired
    WinInfoService winInfoService;



    
    @Test
    @WithAnonymousUser
    @DisplayName("익명의 유저는 CustomException 없이 구매가 가능합니다.")
    public void buyOne(){
        Assertions.assertDoesNotThrow(() -> {
            simulationService.buyOneLottoByUser();
        });
    }

    @Test
    @DisplayName("유저에 대한 내용을 입력하지 않았을 경우 CustomException 발생합니다.")
    public void buyOneWithoutMemberInfo(){
        Assertions.assertThrows(CustomException.class,()->{
            simulationService.buyOneLottoByUser();
        });
    }

    @Test
    @DisplayName("buyBundle은 Cnt만큼 구매되어야 합니다.")
    public void buyBundle(){
        int Cnt = 10;
        List<BuyResultDto> result = simulationService.buyBundle(Cnt);
        Assertions.assertEquals(Cnt,result.size());
    }

    @Test
    @DisplayName("1등당첨시까지 구매로직은 역산해서 동일한 금액이 나와야 합니다.")
    public void buyUntilFirstPlace(){
        BuyUtilFirstPlaceDto result = simulationService.untilFirstPlace();
        WinInfoDto info = winInfoService.infoOfThisWeek();
        long earnedMoney = info.getFirstPrize()
                + result.getNotFirstButPrize()[0] * info.getSecondPrize()
                + result.getNotFirstButPrize()[1] * info.getThirdPrize()
                + result.getNotFirstButPrize()[2] * info.getFourthPrize()
                + result.getNotFirstButPrize()[3] * 5000;
        Assertions.assertEquals(earnedMoney - result.getMoney(),result.getProfit());
    }

}