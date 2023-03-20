package com.jamddo.lotto.repository;

import com.jamddo.lotto.domain.WinInfo;
import com.jamddo.lotto.domain.WinNum;
import com.jamddo.lotto.dto.NumStatisticDto;
import com.jamddo.lotto.dto.WinInfoDto;
import com.jamddo.lotto.dto.WinningNumDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WinInfoRepositoryTest {
    @Autowired
    private WinInfoRepository winInfoRepository;
    @Autowired
    private WinNumRepository winNumRepository;

    @Autowired
    private EntityManager em;

    static final long TEMPORAL_ROUND = 10_000_000L;

    @BeforeEach
    public void init(){
        WinNum winNum = WinNum.builder()
                .id(TEMPORAL_ROUND)
                .firstNum(1)
                .secondNum(3)
                .thirdNum(5)
                .fourthNum(6)
                .fifthNum(7)
                .sixthNum(8)
                .bonusNum(9)
                .build();
        winNumRepository.save(winNum);


        WinInfo winInfo = WinInfo.builder()
                .id(TEMPORAL_ROUND)
                .date(LocalDate.now())
                .firstPrize(10_000_000_000L)
                .secondPrize(1_000_000_000L)
                .thirdPrize(100_000_000L)
                .fourthPrize(10_000_000L)
                .firstPrizeBeneficiaryNum(1)
                .secondPrizeBeneficiaryNum(10)
                .thirdPrizeBeneficiaryNum(100)
                .fourthPrizeBeneficiaryNum(1000)
                .winNum(winNum)
                .build();

        winInfoRepository.save(winInfo);
        em.flush();
        em.clear();

        log.info("데이터 삽입 완료");
    }


    @Test
    @DisplayName("데이터 조회, 삽입된 데이터가 잘 잘 들어가 있고 삽입되지 않은 데이터는 없어야 합니다.")
    public void findData(){
        Optional<WinInfo> existingData = winInfoRepository.findById(TEMPORAL_ROUND);
        Assertions.assertNotNull(existingData);
        Optional<WinInfo> nonExistingData = winInfoRepository.findById(8888L);
        Assertions.assertEquals("Optional.empty",nonExistingData.toString());
    }

    @Test
    @DisplayName("테스트를 위해 넣은 정보의 통계값은 0이 될수 없습니다.")
    public void statisticResult(){
        NumStatisticDto numStatisticDto = winInfoRepository.NumStatistic();
        WinningNumDto winningNumDto = winInfoRepository.winningNumOfThisWeek();
        Assertions.assertNotEquals(0, numStatisticDto.getStatisticResult()[winningNumDto.getFirstNum()]);
    }



}