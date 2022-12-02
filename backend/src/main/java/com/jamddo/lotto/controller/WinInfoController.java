package com.jamddo.lotto.controller;


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
    // 이번주 관련
    @ApiOperation(value ="이번주 1위 당첨번호만")
    @GetMapping("/winningNum")
    public ResponseEntity winningNum(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    winInfoService.winningNumOfThisWeek()
            );
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @ApiOperation(value ="이번주 회차, 당첨번호, 상금, 당첨자 수")
    @GetMapping("/overview")
    public ResponseEntity overview(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    winInfoService.infoOfThisWeek()
            );
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 통계 관련
    @ApiOperation(value ="당첨번호 통계")
    @GetMapping("/statistics/number")
    public ResponseEntity numStatistic(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    winInfoService.NumStatistic()
            );
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @ApiOperation(value ="당첨번호 통계 - 색상별로 묶기")
    @GetMapping("/statistics/number/color")
    public ResponseEntity ColorStatistic(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    winInfoService.ColorStatistic()
            );
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
