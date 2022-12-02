package com.jamddo.board.repository;


import com.jamddo.board.dto.CommentDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.jamddo.board.domain.QBoard.board;
import static com.jamddo.board.domain.QComment.comment;
import static com.jamddo.user.domain.QUser.user;

public class CommentRepositoryImpl implements CommentRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public CommentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public CommentDto findComment(Long commentId) {
        return queryFactory
                .select(Projections.constructor(CommentDto.class,
                        user.nickname,
                        comment.content,
                        comment.board.id
                ))
                .from(comment)
                .innerJoin(comment.user,user)
                .where(comment.id.eq(commentId))
                .fetchOne();
    }

    @Override
    public List<CommentDto> findAllComment(Long boardId) {
        return queryFactory
                .select(Projections.constructor(CommentDto.class,
                        user.nickname,
                        comment.content,
                        comment.board.id
                ))
                .from(comment)
                .innerJoin(comment.user,user)
                .where(comment.board.id.eq(boardId))
                .fetch();

    }
}
