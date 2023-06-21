package com.jamddo.lotto.repository;

import com.jamddo.lotto.domain.history.LottoHistory;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.jamddo.lotto.domain.history.QLottoHistory.lottoHistory;

public class LottoHistoryRepositoryCustomImpl implements LottoHistoryRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public LottoHistoryRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public LottoHistory findRecent() {
       return queryFactory.selectFrom(lottoHistory)
               .orderBy(lottoHistory.round.desc())
               .fetchFirst();

    }

    @Override
    public List<LottoHistory> findAllNumbers() {
        return queryFactory.select(lottoHistory)
                .from(lottoHistory)
                .fetch();
    }


}
