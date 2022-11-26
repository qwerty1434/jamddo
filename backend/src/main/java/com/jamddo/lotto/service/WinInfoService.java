package com.jamddo.lotto.service;

import com.jamddo.lotto.dto.LottoDto;
import com.jamddo.lotto.dto.ResultDto;
import com.jamddo.lotto.repository.WinInfoRepository;
import com.jamddo.lotto.utils.Lotto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WinInfoService {
    private final WinInfoRepository winInfoRepository;
    private final Lotto lotto;

    @Transactional
    public ResultDto buyOne(){
        // 로또 하나 구매하기
        LottoDto myLotto = lotto.Generate();
        // 결과
        return lotto.Scoring(myLotto);
    }
}
