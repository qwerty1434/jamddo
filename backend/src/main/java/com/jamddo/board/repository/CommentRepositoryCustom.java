package com.jamddo.board.repository;



import com.jamddo.board.dto.CommentDto;

import java.util.List;

public interface CommentRepositoryCustom {
    CommentDto findComment(Long commentId);
    List<CommentDto> findAllComment(Long boardId);
}
