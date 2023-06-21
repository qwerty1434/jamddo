package com.jamddo.lotto.service;

import com.jamddo.lotto.domain.lotto.Lotto;
import com.jamddo.lotto.domain.LottoShop;
import com.jamddo.lotto.domain.winInfo.LottoHistory;
import com.jamddo.lotto.dto.BuyUntilFirstResponse;
import com.jamddo.lotto.dto.LottoResultResponse;
import com.jamddo.lotto.repository.LottoHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimulationService {
    private final LottoHistoryRepository lottoHistoryRepository;

    @Transactional
    public LottoResultResponse buyOneLotto(){
        Lotto lotto = LottoShop.buyOne();
        LottoHistory lottoHistory = lottoHistoryRepository.findRecent();
        return LottoResultResponse.of(lotto,lottoHistory);
    }

    @Transactional
    public List<LottoResultResponse> buyBundle(int cnt){
        List<Lotto> lottos = LottoShop.buyBundle(cnt);
        LottoHistory lottoHistory = lottoHistoryRepository.findRecent();
        return lottos.stream()
                .map(lotto -> LottoResultResponse.of(lotto, lottoHistory))
                .sorted(getResponseDtoComparator())
                .collect(Collectors.toList());
    }

    private static Comparator<LottoResultResponse> getResponseDtoComparator() {
        return (o1, o2) -> {
            if (o1.getRank() == -1 || o2.getRank() == -1)
                return o2.getRank() - o1.getRank();
            return o1.getRank() - o2.getRank();
        };
    }

    @Transactional
    public BuyUntilFirstResponse untilFirstPlace(){
        LottoHistory lottoHistory = lottoHistoryRepository.findRecent();
        return LottoShop.buyUntilFirstPlace(lottoHistory);
    }

}
