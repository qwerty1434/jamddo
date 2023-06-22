package com.jamddo.lotto.repository;

import com.jamddo.lotto.domain.winInfo.LottoHistory;

import java.util.List;

public interface LottoHistoryRepositoryCustom {
    LottoHistory findRecent();

    List<LottoHistory> findAllNumbers();
}
