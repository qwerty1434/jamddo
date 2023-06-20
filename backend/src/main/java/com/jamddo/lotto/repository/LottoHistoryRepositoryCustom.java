package com.jamddo.lotto.repository;

import com.jamddo.lotto.domain.LottoNumbers;
import com.jamddo.lotto.domain.history.LottoHistory;

import java.util.List;

public interface LottoHistoryRepositoryCustom {
    LottoHistory findRecent();

    List<LottoNumbers> findAllNumbers();
}
