package com.jamddo.board.service;

import com.jamddo.board.domain.Board;
import com.jamddo.board.domain.Comment;
import com.jamddo.board.dto.CommentDto;
import com.jamddo.board.dto.CreateCommentRequest;
import com.jamddo.board.repository.BoardRepository;
import com.jamddo.board.repository.CommentRepository;
import com.jamddo.user.domain.User;
import com.jamddo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long createBoard(CreateCommentRequest createCommentRequest) {
        Long userId = createCommentRequest.getUserId();
        User user = userRepository.getReferenceById(userId);

        Long boardId = createCommentRequest.getBoardId();
        Board board = boardRepository.getReferenceById(boardId);

        String content = createCommentRequest.getContent();

        Comment comment = Comment.builder()
                .content(content)
                .user(user)
                .board(board)
                .build();


        return commentRepository.save(comment).getId();
    }

    public CommentDto getComment(Long commentId) {
        return commentRepository.findComment(commentId);
    }

    public List<CommentDto> getAllComment(Long boardId) {
        return commentRepository.findAllComment(boardId);
    }

}
