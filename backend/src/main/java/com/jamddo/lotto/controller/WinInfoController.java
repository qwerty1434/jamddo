package com.jamddo.lotto.controller;

import com.jamddo.lotto.service.SimulationService;
import com.jamddo.lotto.service.WinInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/info")
public class WinInfoController {
    private final WinInfoService winInfoService;

    @ApiOperation(value ="이번주 1위 당첨번호만")
    @GetMapping("/winningNum")
    public ResponseEntity winningNum(){
        return ResponseEntity.status(HttpStatus.OK).body(
                winInfoService.winningNumOfThisWeek()
        );
    }
    @ApiOperation(value ="이번주 회차, 당첨번호, 상금, 당첨자 수")
    @GetMapping("/overview")
    public ResponseEntity overview(){
        return ResponseEntity.status(HttpStatus.OK).body(
                winInfoService.infoOfThisWeek()
        );
    }

    // 통계 관련
    @ApiOperation(value ="당첨번호 통계")
    @GetMapping("/statistics/number/{limitCnt}")
    public ResponseEntity numStatistic(@PathVariable int limitCnt){
        return ResponseEntity.status(HttpStatus.OK).body(
                winInfoService.NumStatistic(limitCnt)
        );
    }

    @ApiOperation(value ="당첨번호 통계 - 색상별로 묶기")
    @GetMapping("/statistics/number/color/{limitCnt}")
    public ResponseEntity ColorStatistic(@PathVariable int limitCnt){
        return ResponseEntity.status(HttpStatus.OK).body(
                winInfoService.ColorStatistic(limitCnt)
        );
    }

}
