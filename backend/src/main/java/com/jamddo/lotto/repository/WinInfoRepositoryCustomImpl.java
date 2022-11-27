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
    public NumStatisticDto NumStatistic(int limitCnt) {
        // 0이하의 숫자를 넣으면 전체조회
        if(limitCnt<=0){
            limitCnt = Integer.MAX_VALUE;
        }
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
                .limit(limitCnt)
                .fetch();

        int[] statisticResult = new int[46];
        int[] bonusResult = new int[46];
        for (WinningNumDto dto:result) {
            statisticResult[dto.getFirstNum()]++;
            statisticResult[dto.getSecondNum()]++;
            statisticResult[dto.getThirdNum()]++;
            statisticResult[dto.getFourthNum()]++;
            statisticResult[dto.getFifthNum()]++;
            statisticResult[dto.getSixthNum()]++;
            bonusResult[dto.getBonusNum()]++;
        }

        return NumStatisticDto.builder().statisticResult(statisticResult).bonusResult(bonusResult).build();
    }

}
