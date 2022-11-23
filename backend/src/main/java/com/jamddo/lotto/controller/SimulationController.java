package com.jamddo.lotto.controller;

import com.jamddo.lotto.repository.WinInfoRepository;
import com.jamddo.lotto.service.WinInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SimulationController {
    private final WinInfoService winInfoService;
    @ApiOperation(value ="로또 하나를 샀을 때 결과")
    @GetMapping("/buyOne")
    public ResponseEntity findLocalCategoryHobbyList(){
        return ResponseEntity.status(HttpStatus.OK).body(
                winInfoService.buyOne()
        );
    }
}
