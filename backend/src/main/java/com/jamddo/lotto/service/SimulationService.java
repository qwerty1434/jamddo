package com.jamddo.lotto.service;

import com.jamddo.global.exception.CustomException;
import com.jamddo.lotto.dto.*;
import com.jamddo.lotto.utils.Lotto;
import com.jamddo.user.domain.User;
import com.jamddo.user.repository.UserRepository;
import com.jamddo.user.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.jamddo.global.exception.ErrorCode.MEMBER_NOT_FOUND;

@Service
public class SimulationService {
    private final Lotto lotto;
    private final WinInfoService winInfoService;
    private final UserRepository userRepository;
    private final WinInfoDto winInfoDto;

    public SimulationService(Lotto lotto, WinInfoService winInfoService, UserRepository userRepository) {
        this.lotto = lotto;
        this.winInfoService = winInfoService;
        this.userRepository = userRepository;
        this.winInfoDto = winInfoService.infoOfThisWeek();
    }

    @Transactional
    public LottoDto buyOneLotto(){
        return lotto.Generate();
    }
    @Transactional
    public BuyResultDto scoring(LottoDto myLotto, WinInfoDto winInfoDto){
        return lotto.Scoring(myLotto,winInfoDto);
    }
    @Transactional
    public BuyResultDto buyOneLottoByUser(){
        LottoDto myLotto = buyOneLotto();
        BuyResultDto result = scoring(myLotto,winInfoDto);
        String nickname = SecurityUtil.getCurrentUsername().orElseThrow(()->new CustomException(MEMBER_NOT_FOUND));

        if(nickname.equals("anonymousUser") || nickname.equals("anonymous")) return result;

        User user = userRepository.findByNickname(nickname).orElseThrow(()-> new CustomException(MEMBER_NOT_FOUND));
        user.addCnt();
        user.addPoint(result.getWinningPrize());
        return result;
    }

    @Transactional
    public List<BuyResultDto> buyBundle(int Cnt){
        List<BuyResultDto> result = new ArrayList<>();
        for (int i = 0; i < Cnt; i++) {
            LottoDto myLotto = buyOneLotto();
            result.add(scoring(myLotto,winInfoDto));
        }
        orderAscendingExceptMinusOne(result);

        return result;
    }

    // 기본적으로 오름차순 이지만 -1은 맨 뒤로 보내는 정렬
    public void orderAscendingExceptMinusOne(List<BuyResultDto> result){
        Collections.sort(result, (o1, o2) -> {
            if(o1.getRank() == -1 || o2.getRank() == -1) return o2.getRank() - o1.getRank();
            return o1.getRank() - o2.getRank();
        });

    }

    @Transactional
    public BuyUtilFirstPlaceDto untilFirstPlace(){
        int cnt = 0;
        long money = 0;
        int[] notFirstButPrize = new int[4];
        while(true){
            cnt++;
            money+=1000;
            LottoDto myLotto = buyOneLotto();
            BuyResultDto result = scoring(myLotto,winInfoDto);
            if(result.getRank() == 1) break;
            else if(result.getRank() == 2) {
                notFirstButPrize[0]++;
            }
            else if(result.getRank() == 3) {
                notFirstButPrize[1]++;
            }
            else if(result.getRank() == 4) {
                notFirstButPrize[2]++;
            }
            else if(result.getRank() == 5) {
                notFirstButPrize[3]++;
            }
        }

        // 얼마의 이득 혹은 손해를 봤는지
        long profit =
                winInfoDto.getFirstPrize()
                + (notFirstButPrize[0] *( winInfoDto.getSecondPrize()))
                + (notFirstButPrize[1] *( winInfoDto.getThirdPrize()))
                + (notFirstButPrize[2] *( winInfoDto.getFourthPrize()))
                + notFirstButPrize[3] *(5000)
                - money;

        return BuyUtilFirstPlaceDto.builder()
                .cnt(cnt)
                .money(money)
                .notFirstButPrize(notFirstButPrize)
                .profit(profit)
                .build();
    }

    @Transactional
    public WinningNumArrOnlyDto winningNumOnly(){
        return winInfoService.WinningNumOnly();
    }
}
