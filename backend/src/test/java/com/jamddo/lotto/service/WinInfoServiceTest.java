package com.jamddo.lotto.service;

import com.jamddo.lotto.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("멀티쓰레드 환경에서 deadlock없이 진행")
    public void multiThreadTest(){
    }




    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }



}