package com.jamddo.speetto.repository;

import com.jamddo.speetto.domain.Speetto;
import com.jamddo.speetto.dto.SpeettoDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.Optional;

import static com.jamddo.speetto.domain.QSpeetto.speetto;

@Repository
public class SpeettoRepositoryCustomImpl implements SpeettoRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public SpeettoRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }



    @Override
    public Speetto findUniqueDataAndGetEntity(){
        return queryFactory.selectFrom(speetto).fetchFirst();
    }


    @Override
    public SpeettoDto findUniqueDataAndGetDto() {
        return queryFactory
                .select(Projections.constructor(SpeettoDto.class,
                        speetto.round,
                        speetto.remainCntOfFirstTicket,
                        speetto.remainCntOfSecondTicket,
                        speetto.remainCntOfThirdTicket,
                        speetto.remainCntOfFourthTicket,
                        speetto.remainCntOfFifthTicket,
                        speetto.remainCntOfLosingTicket
                        ))

                .from(speetto)
                .fetchFirst();

    }
}
