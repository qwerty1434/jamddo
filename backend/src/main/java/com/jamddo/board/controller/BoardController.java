package com.jamddo.board.controller;

import com.jamddo.board.dto.CreateBoardRequest;
import com.jamddo.board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @ApiOperation(value="게시글 생성")
    @PostMapping("/create")
    public ResponseEntity createBoard(CreateBoardRequest createBoardRequest){
        try{
            boardService.createBoard(createBoardRequest);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation(value="게시글 상세정보")
    @GetMapping("/{boardId}")
    public ResponseEntity getBoard(@PathVariable Long boardId){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    boardService.getBoard(boardId)
            );
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation(value="모든 게시글")
    @GetMapping("/boardAll")
    public ResponseEntity getAllBoard(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    boardService.getAllBoard()
            );
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
