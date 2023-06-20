package com.jamddo.lotto.dto;

import com.jamddo.lotto.domain.Lotto;
import com.jamddo.lotto.domain.LottoNumbers;
import com.jamddo.lotto.domain.Rank;
import com.jamddo.lotto.domain.history.LottoHistory;

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

    public Rank getRank() {
        return rank;
    }
}
