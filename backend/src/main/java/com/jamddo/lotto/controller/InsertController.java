package com.jamddo.lotto.controller;

import com.jamddo.lotto.domain.winInfo.LottoHistory;
import com.jamddo.lotto.service.InsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/insert")
public class InsertController {
    private final InsertService insertService;

    //데이터 입력 API

    @PostMapping("/number/data")
    public ResponseEntity insertNumberData(@RequestBody LottoHistory data){
        insertService.save(data);
        return ResponseEntity.ok().build();
    }
}
