package com.jamddo.lotto.service;

import com.jamddo.global.exception.CustomException;
import com.jamddo.lotto.dto.BuyResultDto;
import com.jamddo.lotto.dto.BuyUtilFirstPlaceDto;
import com.jamddo.lotto.dto.LottoDto;
import com.jamddo.lotto.dto.WinInfoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@Transactional
class SimulationServiceTest {
    @Autowired
    SimulationService simulationService;

    @Autowired
    WinInfoService winInfoService;


    @Test
    @DisplayName("구매한 번호에 중복이 발생했는지 확인")
    public void buyNotDuplicateTest(){
        LottoDto lottoDto = simulationService.buy();
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
    
    @Test
    @WithAnonymousUser
    @DisplayName("익명의 유저는 CustomException 없이 통과")
    public void buyOne(){
        Assertions.assertDoesNotThrow(() -> {
            simulationService.buyOne();
        });
    }

    @Test
    @DisplayName("유저에 대한 내용을 입력하지 않았을 경우 CustomException 발생")
    public void buyOneWithoutMemberInfo(){
        Assertions.assertThrows(CustomException.class,()->{
            simulationService.buyOne();
        });
    }

    @Test
    @DisplayName("buyBundle은 Cnt만큼 구매되어야 함")
    public void buyBundle(){
        int Cnt = 10;
        List<BuyResultDto> result = simulationService.buyBundle(Cnt);
        Assertions.assertEquals(Cnt,result.size());
    }

    @Test
    @DisplayName("이번주 당첨 정보로 역산했을 때 금액이 일치해야 함")
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