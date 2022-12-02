package com.jamddo.board.repository;

import com.jamddo.board.dto.BoardDto;

import java.util.List;

public interface BoardRepositoryCustom{
    BoardDto findBoard(Long boardId);
    List<BoardDto> findAllBoard();
}
