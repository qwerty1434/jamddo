package com.jamddo.speetto.controller;

import com.jamddo.speetto.service.SpeettoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/speetto")
public class speettoController {
    private final SpeettoService speettoService;
    @ApiOperation(value ="현재 값 가져오기")
    @GetMapping("/getCurrentData")
    public ResponseEntity getCurrentData(){
        return ResponseEntity.status(HttpStatus.OK).body(speettoService.getData());
    }

    @ApiOperation(value ="하나 구매하기")
    @GetMapping("/buyOne")
    public ResponseEntity buyOne(){
        int rank = speettoService.buyOne();
        return ResponseEntity.status(HttpStatus.OK).body(rank);
    }

}
