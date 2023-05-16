package com.jamddo.lotto.controller;



import com.jamddo.global.exception.CustomException;
import com.jamddo.lotto.service.WinInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        }catch(CustomException e){
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getErrorCode().getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("알수없는 오류로 요청이 거부됐습니다.");
        }
    }
    @ApiOperation(value ="이번주 회차, 당첨번호, 상금, 당첨자 수")
    @GetMapping("/overview")
    public ResponseEntity overview(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    winInfoService.infoOfThisWeek()
            );
        }catch(CustomException e){
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getErrorCode().getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("알수없는 오류로 요청이 거부됐습니다.");
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
        }catch(CustomException e){
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getErrorCode().getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("알수없는 오류로 요청이 거부됐습니다.");
        }
    }
    @ApiOperation(value ="당첨번호 통계 - 색상별로 묶기")
    @GetMapping("/statistics/number/color")
    public ResponseEntity colorStatistic(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    winInfoService.ColorStatistic()
            );
        }catch(CustomException e){
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getErrorCode().getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("알수없는 오류로 요청이 거부됐습니다.");
        }
    }




}
