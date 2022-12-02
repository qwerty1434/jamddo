package com.jamddo.board.repository;

import com.jamddo.board.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long>, CommentRepositoryCustom {

}
