package com.jamddo.lotto.repository;

import com.jamddo.lotto.dto.WinInfoDto;
import com.jamddo.lotto.dto.WinningNumsDto;

public interface WinInfoRepositoryCustom {
    // 이번회차 당첨번호
    WinningNumsDto winningNumOfThisWeek();

    // 이번회차 정보
    WinInfoDto InfoOfThisWeek();
}
