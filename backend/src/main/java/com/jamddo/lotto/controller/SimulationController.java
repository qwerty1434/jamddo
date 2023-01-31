package com.jamddo.lotto.controller;

import com.jamddo.global.exception.CustomException;
import com.jamddo.lotto.service.SimulationService;
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
@RequestMapping("/simulation")
public class SimulationController {
    private final SimulationService simulationService;
    @ApiOperation(value ="로또 하나를 구매")
    @GetMapping("/buyOne")
    public ResponseEntity buyOne(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    simulationService.buyOne()
            );
        }catch(CustomException e){
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getErrorCode().getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("알수없는 오류로 요청이 거부됐습니다.");
        }

    }

    @ApiOperation(value ="로또 N개 구매")
    @GetMapping("/buyBundle/{Cnt}")
    public ResponseEntity buyBundle(@PathVariable int Cnt){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    simulationService.buyBundle(Cnt)
            );
        }catch(CustomException e){
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getErrorCode().getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("알수없는 오류로 요청이 거부됐습니다.");
        }

    }
    
    @ApiOperation(value = "1등 당첨될 때까지 구매")
    @GetMapping("/untilFirstPlace")
    public ResponseEntity untilFirstPlace(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    simulationService.untilFirstPlace()
            );
        }catch(CustomException e){
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getErrorCode().getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("알수없는 오류로 요청이 거부됐습니다.");
        }
    }

    @ApiOperation(value ="이번주 1등 정보를 숫자만 반환, 당첨된 번호에 하이라이트 처리할 때 사용")
    @GetMapping("/winningNumOnly")
    public ResponseEntity winningNumOnly(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    simulationService.winningNumOnly()
            );

        }catch(CustomException e){
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getErrorCode().getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("알수없는 오류로 요청이 거부됐습니다.");
        }
    }


}
