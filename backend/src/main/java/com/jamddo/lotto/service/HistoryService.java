package com.jamddo.lotto.service;

import com.jamddo.lotto.domain.lotto.LottoNumber;
import com.jamddo.lotto.domain.lotto.LottoNumbers;
import com.jamddo.lotto.domain.winInfo.Beneficiaries;
import com.jamddo.lotto.domain.winInfo.LottoHistory;
import com.jamddo.lotto.domain.winInfo.Rewards;
import com.jamddo.lotto.dto.WinningInfoResponse;
import com.jamddo.lotto.dto.WinningNumResponse;
import com.jamddo.lotto.repository.LottoHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HistoryService {
    private final LottoHistoryRepository lottoHistoryRepository;

    public WinningNumResponse getWinningNumOfThisWeek() {
        LottoNumbers lottoNumbers = lottoHistoryRepository.findRecent().getLottoNumbers();
        LottoNumber bonusNumber = lottoHistoryRepository.findRecent().getBonusNumber();
        return WinningNumResponse.of(lottoNumbers,bonusNumber);
    }

    public WinningInfoResponse getInfoOfThisWeek(){
        int round = lottoHistoryRepository.findRecent().getRound();
        Rewards rewards = lottoHistoryRepository.findRecent().getRewards();
        Beneficiaries beneficiaries = lottoHistoryRepository.findRecent().getBeneficiaries();
        return WinningInfoResponse.of(round,rewards,beneficiaries);
    }

    public int[] getAllNumberStatistic() {
        int[] statistic = new int[46];
        List<LottoNumbers> result = lottoHistoryRepository.findAllNumbers().stream()
                .map(LottoHistory::getLottoNumbers)
                .collect(Collectors.toList());
        for (LottoNumbers lottoNumbers : result) {
            for (LottoNumber lottoNumber : lottoNumbers.getLottoNumbers()) {
                statistic[lottoNumber.getLottoNumber()]++;
            }
        }
        return statistic;
    }

    public int[] getColorStatistic(){
        int[] AllNumberStatistic = getAllNumberStatistic();
        int[] colorStatistic = new int[5];
        for (int i = 1; i <= 45 ; i++){
            if(i<=10){
                colorStatistic[0] += AllNumberStatistic[i];
            }else if(10 < i && i <= 20){
                colorStatistic[1] += AllNumberStatistic[i];
            }else if(20 < i && i <= 30){
                colorStatistic[2] += AllNumberStatistic[i];
            }else if(30 < i && i <= 40){
                colorStatistic[3] += AllNumberStatistic[i];
            }else if(40 < i && i <= 45){
                colorStatistic[4] += AllNumberStatistic[i];
            }
        }
        return colorStatistic;
    }

}
