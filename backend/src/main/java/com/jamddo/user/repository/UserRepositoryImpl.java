package com.jamddo.user.repository;

import com.jamddo.user.domain.QUser;
import com.jamddo.user.dto.RankingDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.jamddo.user.domain.QUser.user;

public class UserRepositoryImpl implements UserRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<RankingDto> findAllOrderByPriceDesc() {
        return queryFactory
                .select(Projections.constructor(RankingDto.class,
                    user.nickname,
                    user.point
                ))
                .from(user)
                .orderBy(user.point.desc())
                .fetch();
    }
}
