package com.jamddo.lotto.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jamddo.lotto.domain.WinInfo;
import com.jamddo.lotto.domain.WinNum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WinInfoRequestDto {
    private Long round;

    // DateTimeFormat과 JsonFormat을 둘 다 사용해야 정상적으로 동작합니다.
    @DateTimeFormat(pattern="yyyy.MM.dd")
    @JsonFormat(pattern ="yyyy.MM.dd")
    private LocalDate date;

    // 상금
    private long firstPrize;
    private long secondPrize;
    private long thirdPrize;
    private long fourthPrize;

    // 당첨자 수
    private int firstPrizeBeneficiaryNum;
    private int secondPrizeBeneficiaryNum;
    private int thirdPrizeBeneficiaryNum;
    private int fourthPrizeBeneficiaryNum;

    public WinInfo dtoToEntity(WinNum winNum){
        return WinInfo.builder()
                .id(this.round)
                .date(this.date)
                .winNum(winNum)
                .firstPrize(this.firstPrize)
                .secondPrize(this.secondPrize)
                .thirdPrize(this.thirdPrize)
                .fourthPrize(this.fourthPrize)
                .firstPrizeBeneficiaryNum(this.firstPrizeBeneficiaryNum)
                .secondPrizeBeneficiaryNum(this.secondPrizeBeneficiaryNum)
                .thirdPrizeBeneficiaryNum(this.thirdPrizeBeneficiaryNum)
                .fourthPrizeBeneficiaryNum(this.fourthPrizeBeneficiaryNum)
                .build();
    }
}
