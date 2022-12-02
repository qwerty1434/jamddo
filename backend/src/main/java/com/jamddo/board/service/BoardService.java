package com.jamddo.board.service;

import com.jamddo.board.domain.Board;
import com.jamddo.board.dto.BoardDto;
import com.jamddo.board.dto.CreateBoardRequest;
import com.jamddo.board.repository.BoardRepository;
import com.jamddo.user.domain.User;
import com.jamddo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createBoard(CreateBoardRequest createBoardRequest) {
        Long userId = createBoardRequest.getUserId();
        User user = userRepository.getReferenceById(userId);

        String content = createBoardRequest.getContent();

        Board board = Board.builder()
                .content(content)
                .user(user)
                .build();

        return boardRepository.save(board).getId();
    }

    public BoardDto getBoard(Long board_id) {
        return boardRepository.findBoard(board_id);
    }

    public List<BoardDto> getAllBoard() {
        return boardRepository.findAllBoard();
    }
}
