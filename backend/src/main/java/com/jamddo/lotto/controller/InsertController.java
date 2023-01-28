package com.jamddo.lotto.controller;

import com.jamddo.global.exception.CustomException;
import com.jamddo.lotto.dto.WinInfoRequestDto;
import com.jamddo.lotto.dto.WinningNumDto;
import com.jamddo.lotto.service.InsertService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ApiOperation(value ="이번주 당첨번호 입력")
    @PostMapping("/number/data")
    public ResponseEntity insertNumberData(@RequestBody WinningNumDto data){
        try{
            insertService.number(data);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(CustomException e){
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getErrorCode().getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("알수없는 오류로 요청이 거부됐습니다.");
        }
    }
    @ApiOperation(value ="이번주 당첨 정보 입력")
    @PostMapping("/winInfo/data")
    public ResponseEntity insertWinInfoData(@RequestBody WinInfoRequestDto data){
        try{
            insertService.statistic(data);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(CustomException e){
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getErrorCode().getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("알수없는 오류로 요청이 거부됐습니다.");
        }
    }
}
