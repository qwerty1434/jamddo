package com.jamddo.board.controller;

import com.jamddo.board.dto.CreateCommentRequest;
import com.jamddo.board.service.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @ApiOperation(value="댓글 생성")
    @PostMapping("/create")
    public ResponseEntity createComment(CreateCommentRequest createCommentRequest){
        try{
            commentService.createComment(createCommentRequest);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation(value="댓글 상세정보")
    @GetMapping("/{commentId}")
    public ResponseEntity getComment(@PathVariable Long commentId){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    commentService.getComment(commentId)
            );
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation(value="모든 댓글")
    @GetMapping("/commentAll/{boardId}")
    public ResponseEntity getAllComment(@PathVariable Long boardId){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    commentService.getAllComment(boardId)
            );
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



}
