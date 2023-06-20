package com.jamddo.lotto.service;

import com.jamddo.global.exception.CustomException;
import com.jamddo.lotto.domain.Lotto;
import com.jamddo.lotto.domain.LottoShop;
import com.jamddo.lotto.domain.history.LottoHistory;
import com.jamddo.lotto.dto.BuyUntilFirstPlaceDto;
import com.jamddo.lotto.dto.LottoResultResponse;
import com.jamddo.lotto.repository.LottoHistoryRepository;
import com.jamddo.user.domain.User;
import com.jamddo.user.repository.UserRepository;
import com.jamddo.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.jamddo.global.exception.ErrorCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SimulationService {

    private final LottoHistoryRepository lottoHistoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public LottoResultResponse buyOneLotto(){
        Lotto lotto = LottoShop.buyOne();
        LottoHistory lottoHistory = lottoHistoryRepository.findRecent();
        return LottoResultResponse.of(lotto,lottoHistory);
    }

    @Transactional
    public LottoResultResponse buyOneLottoByUser(){
        LottoResultResponse result = buyOneLotto();
        String nickname = SecurityUtil.getCurrentUsername().orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        if(nickname.equals("anonymousUser") || nickname.equals("anonymous")) return result;

        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        user.addCnt();
        user.addPoint(result.getPrize());
        return result;
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
            if (o1.getRank().getRank() == -1 || o2.getRank().getRank() == -1)
                return o2.getRank().getRank() - o1.getRank().getRank();
            return o1.getRank().getRank() - o2.getRank().getRank();
        };
    }

    @Transactional
    public BuyUntilFirstPlaceDto untilFirstPlace(){
        LottoHistory lottoHistory = lottoHistoryRepository.findRecent();
        return LottoShop.untilFirstPlace(lottoHistory);
    }

}
