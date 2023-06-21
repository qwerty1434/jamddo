package com.jamddo.lotto.dto;

import com.jamddo.lotto.domain.Lotto;
import com.jamddo.lotto.domain.LottoNumbers;
import com.jamddo.lotto.domain.Rank;
import com.jamddo.lotto.domain.history.LottoHistory;

import java.util.List;

public class LottoResultResponse {
    LottoNumbers lottoNumbers;
    Rank rank;

    private LottoResultResponse(Lotto lotto, LottoHistory lottoHistory) {
        this.lottoNumbers = lotto.getLottoNumbers();
        this.rank = lotto.getRank(lottoHistory);
    }

    public static LottoResultResponse of(Lotto lotto, LottoHistory lottoHistory){
        return new LottoResultResponse(lotto,lottoHistory);
    }

    public long getPrize(){
        return rank.getPrize();
    }

    public int getRank() {
        return rank.getRank();
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.getLottoNumbersWithInteger();
    }
}
