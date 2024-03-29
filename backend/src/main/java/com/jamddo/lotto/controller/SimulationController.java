package com.jamddo.lotto.controller;

import com.jamddo.lotto.service.SimulationService;

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

    @GetMapping("/buyOne")
    public ResponseEntity buyOne(){
        return ResponseEntity.status(HttpStatus.OK).body(simulationService.buyOneLotto());
    }

    @GetMapping("/buyBundle/{Cnt}")
    public ResponseEntity buyBundle(@PathVariable int Cnt){
            return ResponseEntity.status(HttpStatus.OK).body(simulationService.buyBundle(Cnt));
    }

    @GetMapping("/untilFirstPlace")
    public ResponseEntity untilFirstPlace(){
            return ResponseEntity.status(HttpStatus.OK).body(simulationService.untilFirstPlace());
    }


}
