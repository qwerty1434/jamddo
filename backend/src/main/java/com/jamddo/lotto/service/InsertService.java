package com.jamddo.lotto.service;

import com.jamddo.lotto.domain.history.LottoHistory;
import com.jamddo.lotto.repository.LottoHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InsertService {
    private final LottoHistoryRepository lottoHistoryRepository;

    @Transactional
    public void save(LottoHistory lottoHistory) {
        lottoHistoryRepository.save(lottoHistory);
    }


}
