package com.jamddo.lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoShopTest {

    @DisplayName("대량구매 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1,4,7,10})
    void buyBundleTest(int cnt){
        int lottos = LottoShop.buyBundle(cnt).size();
        assertThat(lottos).isEqualTo(cnt);
    }

}