package com.jamddo.lotto.repository;

import com.jamddo.lotto.dto.NumStatisticDto;
import com.jamddo.lotto.dto.WinInfoDto;
import com.jamddo.lotto.dto.WinningNumDto;

public interface WinInfoRepositoryCustom {
    // 이번회차 당첨번호
    WinningNumDto winningNumOfThisWeek();

    // 이번회차 정보
    WinInfoDto InfoOfThisWeek();

    // 전체 통계정보
    NumStatisticDto NumStatistic(int limitCNt);
}
