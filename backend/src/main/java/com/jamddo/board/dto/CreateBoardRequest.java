package com.jamddo.board.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
public class CreateBoardRequest {
    Long userId;
    String content;

}
