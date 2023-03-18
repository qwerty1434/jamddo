package com.jamddo.lotto.repository;

import com.jamddo.lotto.domain.WinInfo;
import com.jamddo.lotto.domain.WinNum;
import com.jamddo.lotto.dto.NumStatisticDto;
import com.jamddo.lotto.dto.WinInfoDto;
import com.jamddo.lotto.dto.WinningNumDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WinInfoRepositoryTest {
    @Autowired
    private WinInfoRepository winInfoRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void init(){
        WinInfo winInfo = WinInfo.builder()
                .id(9999L)
                .date(LocalDate.now())
                .firstPrize(10_000_000_000L)
                .secondPrize(1_000_000_000L)
                .thirdPrize(100_000_000L)
                .fourthPrize(10_000_000L)
                .firstPrizeBeneficiaryNum(1)
                .secondPrizeBeneficiaryNum(10)
                .thirdPrizeBeneficiaryNum(100)
                .fourthPrizeBeneficiaryNum(1000)
                .build();

        winInfoRepository.save(winInfo);
        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("데이터 조회")
    public void findData(){
        Optional<WinInfo> existingData = winInfoRepository.findById(9999L);
        Assertions.assertNotNull(existingData);

        Optional<WinInfo> nonExistingData = winInfoRepository.findById(8888L);
        Assertions.assertEquals("Optional.empty",nonExistingData.toString());
    }

}