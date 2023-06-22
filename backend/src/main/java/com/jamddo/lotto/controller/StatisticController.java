package com.jamddo.lotto.controller;

import com.jamddo.lotto.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/info")
public class StatisticController {
    private final HistoryService historyService;

    @GetMapping("/winningNum")
    public ResponseEntity winningNum(){
        return ResponseEntity.ok().body(historyService.getWinningNumOfThisWeek());
    }

    @GetMapping("/overview")
    public ResponseEntity overview(){
        return ResponseEntity.ok().body(historyService.getInfoOfThisWeek());
    }

    @GetMapping("/statistics/number")
    public ResponseEntity numStatistic(){
        return ResponseEntity.ok().body(historyService.getAllNumberStatistic());
    }

    @GetMapping("/statistics/number/color")
    public ResponseEntity colorStatistic(){
        return ResponseEntity.ok().body(historyService.getColorStatistic());
    }

}
