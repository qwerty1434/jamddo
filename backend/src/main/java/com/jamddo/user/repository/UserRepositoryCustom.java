package com.jamddo.user.repository;

import com.jamddo.user.dto.RankingDto;

import java.util.List;

public interface UserRepositoryCustom {
    // 랭킹정보
    List<RankingDto> findAllOrderByPriceDesc();

}
