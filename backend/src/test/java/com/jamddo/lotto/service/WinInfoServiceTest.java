package com.jamddo.lotto.service;

import com.jamddo.lotto.dto.WinInfoDto;
import com.jamddo.lotto.dto.WinningNumDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class WinInfoServiceTest {
    @Autowired
    WinInfoService winInfoService;

    @Test
    public void 이번주_당첨번호(){
        WinningNumDto result = winInfoService.winningNumOfThisWeek();
        Assertions.assertThatNoException();
    }

    @Test
    public void 이번주_당첨정보(){
        WinInfoDto result = winInfoService.infoOfThisWeek();
        Assertions.assertThatNoException();
    }

    @Test
    public void 동시_테스트(){
        WinningNumDto result = winInfoService.winningNumOfThisWeek();
        WinInfoDto result2 = winInfoService.infoOfThisWeek();
        Assertions.assertThatNoException();

    }


}