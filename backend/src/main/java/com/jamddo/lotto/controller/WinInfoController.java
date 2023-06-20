package com.jamddo.lotto.controller;

import com.jamddo.lotto.service.HistoryService;
import com.querydsl.codegen.ClassPathUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
@RequestMapping("/info")
public class WinInfoController {
    private final HistoryService historyService;

    @ApiOperation(value ="이번주 1위 당첨번호만")
    @GetMapping("/winningNum")
    public ResponseEntity winningNum(){
        return ResponseEntity.ok().body(historyService.getWinningNumOfThisWeek());
    }
    @ApiOperation(value ="이번주 회차, 당첨번호, 상금, 당첨자 수")
    @GetMapping("/overview")
    public ResponseEntity overview(){
        return ResponseEntity.ok().body(historyService.getInfoOfThisWeek());
    }
    @ApiOperation(value ="당첨번호 통계")
    @GetMapping("/statistics/number")
    public ResponseEntity numStatistic(){
        return ResponseEntity.ok().body(historyService.getAllNumberStatistic());
    }
    @ApiOperation(value ="당첨번호 통계 - 색상별로 묶기")
    @GetMapping("/statistics/number/color")
    public ResponseEntity colorStatistic(){
        return ResponseEntity.ok().body(historyService.getColorStatistic());
    }

}
