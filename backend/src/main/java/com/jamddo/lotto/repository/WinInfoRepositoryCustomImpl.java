package com.jamddo.lotto.repository;

import com.jamddo.lotto.dto.NumStatisticDto;
import com.jamddo.lotto.dto.WinInfoDto;
import com.jamddo.lotto.dto.WinningNumDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import static com.jamddo.lotto.domain.QWinInfo.winInfo;
import static com.jamddo.lotto.domain.QWinNum.winNum;

import javax.persistence.EntityManager;
import java.util.List;

public class WinInfoRepositoryCustomImpl implements WinInfoRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public WinInfoRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public WinningNumDto winningNumOfThisWeek() {
        return queryFactory
                .select(Projections.constructor(WinningNumDto.class,
                        winNum.firstNum,
                        winNum.secondNum,
                        winNum.thirdNum,
                        winNum.fourthNum,
                        winNum.fifthNum,
                        winNum.sixthNum,
                        winNum.bonusNum
                ))
                .from(winInfo)
                .orderBy(winInfo.id.desc())
                .fetchFirst();
    }

    @Override
    public WinInfoDto InfoOfThisWeek() {
        return queryFactory
                .select(Projections.constructor(WinInfoDto.class,
                        winInfo.id,
                        winNum.firstNum,
                        winNum.secondNum,
                        winNum.thirdNum,
                        winNum.fourthNum,
                        winNum.fifthNum,
                        winNum.sixthNum,
                        winNum.bonusNum,
                        winInfo.firstPrize,
                        winInfo.secondPrize,
                        winInfo.thirdPrize,
                        winInfo.fourthPrize,
                        winInfo.firstPrizeBeneficiaryNum,
                        winInfo.secondPrizeBeneficiaryNum,
                        winInfo.thirdPrizeBeneficiaryNum,
                        winInfo.fourthPrizeBeneficiaryNum
                ))
                .from(winInfo)
                .orderBy(winInfo.id.desc())
                .fetchFirst();
    }

    @Override
    public NumStatisticDto NumStatistic() {
        List<WinningNumDto> result = queryFactory
                .select(Projections.constructor(WinningNumDto.class,
                        winNum.firstNum,
                        winNum.secondNum,
                        winNum.thirdNum,
                        winNum.fourthNum,
                        winNum.fifthNum,
                        winNum.sixthNum,
                        winNum.bonusNum
                ))
                .from(winInfo)
                .fetch();

        int[] statisticResult = new int[46];
        for (WinningNumDto dto:result) {
            statisticResult[dto.getFirstNum()]++;
            statisticResult[dto.getSecondNum()]++;
            statisticResult[dto.getThirdNum()]++;
            statisticResult[dto.getFourthNum()]++;
            statisticResult[dto.getFifthNum()]++;
            statisticResult[dto.getSixthNum()]++;
        }

        return NumStatisticDto.builder().statisticResult(statisticResult).build();
    }

}
