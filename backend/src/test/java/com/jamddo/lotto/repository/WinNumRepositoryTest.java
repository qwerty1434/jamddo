package com.jamddo.lotto.repository;

import com.jamddo.lotto.domain.WinNum;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class WinNumRepositoryTest {
    @Autowired
    WinNumRepository winNumRepository;

    @BeforeEach
    public void init(){
        Integer[] Lotto = new Random()
                .ints(1, 45 + 1)
                .distinct()
                .limit(7)
                .boxed()
                .sorted()
                .collect(Collectors.toList())
                .toArray(new Integer[0]);


        WinNum winNum = WinNum.builder()
                .id(9999L)
                .firstNum(Lotto[0])
                .secondNum(Lotto[1])
                .thirdNum(Lotto[2])
                .fourthNum(Lotto[3])
                .fifthNum(Lotto[4])
                .sixthNum(Lotto[5])
                .bonusNum(Lotto[6])
                .build();
    }

}