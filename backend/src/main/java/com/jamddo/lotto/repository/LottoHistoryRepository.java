package com.jamddo.lotto.repository;

import com.jamddo.lotto.domain.winInfo.LottoHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoHistoryRepository extends JpaRepository<LottoHistory,Long>, LottoHistoryRepositoryCustom{

}
