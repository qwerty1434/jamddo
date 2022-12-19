package com.jamddo.lotto.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWinNum is a Querydsl query type for WinNum
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWinNum extends EntityPathBase<WinNum> {

    private static final long serialVersionUID = 422484714L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWinNum winNum = new QWinNum("winNum");

    public final NumberPath<Integer> bonusNum = createNumber("bonusNum", Integer.class);

    public final NumberPath<Integer> fifthNum = createNumber("fifthNum", Integer.class);

    public final NumberPath<Integer> firstNum = createNumber("firstNum", Integer.class);

    public final NumberPath<Integer> fourthNum = createNumber("fourthNum", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> secondNum = createNumber("secondNum", Integer.class);

    public final NumberPath<Integer> sixthNum = createNumber("sixthNum", Integer.class);

    public final NumberPath<Integer> thirdNum = createNumber("thirdNum", Integer.class);

    public final QWinInfo winInfo;

    public QWinNum(String variable) {
        this(WinNum.class, forVariable(variable), INITS);
    }

    public QWinNum(Path<? extends WinNum> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWinNum(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWinNum(PathMetadata metadata, PathInits inits) {
        this(WinNum.class, metadata, inits);
    }

    public QWinNum(Class<? extends WinNum> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.winInfo = inits.isInitialized("winInfo") ? new QWinInfo(forProperty("winInfo"), inits.get("winInfo")) : null;
    }

}

