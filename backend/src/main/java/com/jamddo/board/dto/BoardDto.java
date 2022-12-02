package com.jamddo.board.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
public class BoardDto {
    String writerNickname;
    String content;
    List<CommentDto> commentList;
}
