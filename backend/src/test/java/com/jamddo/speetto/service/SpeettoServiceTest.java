package com.jamddo.speetto.service;

import com.jamddo.speetto.domain.Speetto;
import com.jamddo.speetto.dto.SpeettoDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class SpeettoServiceTest {
    @Autowired
    private SpeettoService speettoService;


    @Test
    public void concurrentTest(){
        Runnable userA = () -> {
            speettoService.buyOne();
            log.info("Thread A"+speettoService.getData().totalRemainCnt());
        };
        Thread threadA = new Thread(userA);
        threadA.start();

        Runnable userB = () -> {
            speettoService.buyOne();
            log.info("Thread B"+speettoService.getData().totalRemainCnt());

        };
        Thread threadB = new Thread(userB);
        threadB.start();

        Runnable userC = () -> {
            speettoService.buyOne();
            log.info("Thread C"+speettoService.getData().totalRemainCnt());

        };
        Thread threadC = new Thread(userC);
        threadC.start();

        sleep(4000);
        SpeettoDto speettoDto = speettoService.getData();
        log.info("speetto.totalRemainCnt() = {}", speettoDto.totalRemainCnt());
        Assertions.assertEquals(39999997,speettoDto.totalRemainCnt());


    }

    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}