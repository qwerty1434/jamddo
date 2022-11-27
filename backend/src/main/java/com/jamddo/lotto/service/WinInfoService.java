package com.jamddo.lotto.service;

import com.jamddo.lotto.dto.NumStatisticDto;
import com.jamddo.lotto.dto.WinInfoDto;
import com.jamddo.lotto.dto.WinningNumDto;
import com.jamddo.lotto.repository.WinInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WinInfoService {
    private final WinInfoRepository winInfoRepository;

    public WinningNumDto winningNumOfThisWeek() {
        return winInfoRepository.winningNumOfThisWeek();
    }

    public WinInfoDto infoOfThisWeek(){
        return winInfoRepository.InfoOfThisWeek();
    }

    public NumStatisticDto NumStatistic(int limitNum) {
        return winInfoRepository.NumStatistic(limitNum);
    }
}
