package com.jamddo.lotto.controller;

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
        return ResponseEntity.status(HttpStatus.OK).body(
                simulationService.buyOne()
        );
    }

    @ApiOperation(value ="로또 N개 구매")
    @GetMapping("/buyBundle/{Cnt}")
    public ResponseEntity buyBundle(@PathVariable int Cnt){
        return ResponseEntity.status(HttpStatus.OK).body(
                simulationService.buyBundle(Cnt)
        );
    }


    @ApiOperation(value = "1등 당첨될 때까지 구매")
    @GetMapping("/untilFirstPlace")
    public ResponseEntity untilFirstPlace(){
        return ResponseEntity.status(HttpStatus.OK).body(
                simulationService.untilFirstPlace()
        );
    }



}
