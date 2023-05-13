package com.jamddo.lotto.service;

import com.jamddo.lotto.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class WinInfoServiceTest {
    @Autowired
    WinInfoService winInfoService;

    @Test
    @DisplayName("멀티쓰레드 환경에서 deadlock이 발생하지 않아야 합니다.")
    public void multiThreadTest(){
        Runnable userA = () -> {
            winInfoService.winningNumOfThisWeek();
        };
        Thread threadA = new Thread(userA);
        threadA.start();

        Runnable userB = () -> {
            winInfoService.infoOfThisWeek();
        };
        Thread threadB = new Thread(userB);
        threadB.start();

        Runnable userC = () -> {
            winInfoService.NumStatistic();
        };
        Thread threadC = new Thread(userC);
        threadC.start();

        Assertions.assertTimeoutPreemptively(Duration.ofMillis(4000),()->{
            threadA.join();
            threadB.join();
            threadC.join();
        });
    }







}