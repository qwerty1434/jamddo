package com.jamddo.board.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
public class CommentDto {
    String writerNickname;
    String content;
    Long boardId;
}
