package com.jamddo.lotto.service;


import com.jamddo.global.exception.CustomException;
import com.jamddo.global.exception.ErrorCode;
import com.jamddo.lotto.domain.WinInfo;
import com.jamddo.lotto.domain.WinNum;
import com.jamddo.lotto.dto.WinInfoRequestDto;
import com.jamddo.lotto.dto.WinningNumDto;
import com.jamddo.lotto.repository.WinInfoRepository;
import com.jamddo.lotto.repository.WinNumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InsertService {
    private final WinNumRepository winNumRepository;
    private final WinInfoRepository winInfoRepository;

    @Transactional
    public void number(WinningNumDto data){
        winNumRepository.save(data.dtoToEntity());
    }

    @Transactional
    public void statistic(WinInfoRequestDto data){
        WinNum winNum = winNumRepository.findById(data.getRound()).orElseThrow(()->new CustomException(ErrorCode.WINNUM_NOT_FOUND));
        WinInfo result = data.dtoToEntity(winNum);
        winInfoRepository.save(result);
    }

}
