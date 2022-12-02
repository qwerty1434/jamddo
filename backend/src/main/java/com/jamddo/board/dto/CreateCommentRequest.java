package com.jamddo.board.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
public class CreateCommentRequest {
    Long userId;
    Long boardId;
    String content;

}
