package com.jamddo.lotto.repository;


import com.jamddo.lotto.domain.WinInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinInfoRepository extends JpaRepository<WinInfo,Long>, WinInfoRepositoryCustom {
}
