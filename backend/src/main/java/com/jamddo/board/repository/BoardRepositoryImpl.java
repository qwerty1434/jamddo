package com.jamddo.board.repository;


import com.jamddo.board.dto.BoardDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;


import javax.persistence.EntityManager;
import java.util.List;

import static com.jamddo.board.domain.QBoard.board;
import static com.jamddo.user.domain.QUser.user;


public class BoardRepositoryImpl implements BoardRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public BoardDto findBoard(Long boardId) {
        return queryFactory
                .select(Projections.constructor(BoardDto.class,
                        user.nickname,
                        board.content
                ))
                .from(board)
                .innerJoin(board.user,user)
                .where(board.id.eq(boardId))
                .fetchOne();
    }

    @Override
    public List<BoardDto> findAllBoard() {
        return queryFactory
                .select(Projections.constructor(BoardDto.class,
                        user.nickname,
                        board.content
                ))
                .from(board)
                .innerJoin(board.user,user)
                .fetch();
    }
}
