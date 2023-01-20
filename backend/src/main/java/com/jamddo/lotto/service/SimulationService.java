package com.jamddo.lotto.service;

import com.jamddo.global.exception.CustomException;
import com.jamddo.lotto.dto.*;
import com.jamddo.lotto.repository.WinInfoRepository;
import com.jamddo.lotto.utils.Lotto;
import com.jamddo.user.domain.User;
import com.jamddo.user.repository.UserRepository;
import com.jamddo.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.jamddo.global.exception.ErrorCode.MEMBER_NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SimulationService {
    private final Lotto lotto;
    private final WinInfoService winInfoService;
    private final UserRepository userRepository;

    @Transactional
    public LottoDto buy(){
        return lotto.Generate();
    }
    @Transactional
    public BuyResultDto scoring(LottoDto myLotto, WinInfoDto winInfoDto){
        return lotto.Scoring(myLotto,winInfoDto);
    }
    @Transactional
    public BuyResultDto buyOne(){
        LottoDto myLotto = buy();
        WinInfoDto winInfoDto = winInfoService.infoOfThisWeek();
        BuyResultDto result =  scoring(myLotto,winInfoDto);
        String nickname = SecurityUtil.getCurrentUsername().orElseThrow(()->new CustomException(MEMBER_NOT_FOUND));
        if(!nickname.equals("anonymousUser")){
            User user = userRepository.findByNickname(nickname).orElseThrow(()-> new CustomException(MEMBER_NOT_FOUND));
            user.addCnt();
            user.addPoint(result.getWinningPrize());
        }


        return result;
    }

    @Transactional
    public List<BuyResultDto> buyBundle(int Cnt){
        WinInfoDto winInfoDto = winInfoService.infoOfThisWeek();
        List<BuyResultDto> result = new ArrayList<>();
        for (int i = 0; i < Cnt; i++) {
            LottoDto myLotto = buy();
            result.add(scoring(myLotto,winInfoDto));
        }
        // 기본적으로 오름차순 이지만 -1은 맨 뒤로 보내는 정렬
        Collections.sort(result, (o1, o2) -> {
            if(o1.getRank() == -1 || o2.getRank() == -1) return o2.getRank() - o1.getRank();
            return o1.getRank() - o2.getRank();
        });
        return result;

    }

    @Transactional
    public BuyUtilFirstPlaceDto untilFirstPlace(){
        int cnt = 0;
        long money = 0;
        int[] notFirstButPrize = new int[4];
        WinInfoDto winInfoDto = winInfoService.infoOfThisWeek();
        while(true){
            cnt++;
            money+=1000;
            LottoDto myLotto = buy();
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

//        System.out.println("1등 상금:" +winInfoDto.getFirstPrize());
//        System.out.println("2등 상금:" +notFirstButPrize[0] +"||"+( winInfoDto.getSecondPrize()));
//        System.out.println("3등 상금:" +notFirstButPrize[1] +"||"+( winInfoDto.getThirdPrize()));
//        System.out.println("4등 상금:" +notFirstButPrize[2] +"||"+( winInfoDto.getFourthPrize()));


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
