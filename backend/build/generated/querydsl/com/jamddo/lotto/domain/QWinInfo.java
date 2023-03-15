package com.jamddo.lotto.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWinInfo is a Querydsl query type for WinInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWinInfo extends EntityPathBase<WinInfo> {

    private static final long serialVersionUID = 211968458L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWinInfo winInfo = new QWinInfo("winInfo");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final NumberPath<Long> firstPrize = createNumber("firstPrize", Long.class);

    public final NumberPath<Integer> firstPrizeBeneficiaryNum = createNumber("firstPrizeBeneficiaryNum", Integer.class);

    public final NumberPath<Long> fourthPrize = createNumber("fourthPrize", Long.class);

    public final NumberPath<Integer> fourthPrizeBeneficiaryNum = createNumber("fourthPrizeBeneficiaryNum", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> secondPrize = createNumber("secondPrize", Long.class);

    public final NumberPath<Integer> secondPrizeBeneficiaryNum = createNumber("secondPrizeBeneficiaryNum", Integer.class);

    public final NumberPath<Long> thirdPrize = createNumber("thirdPrize", Long.class);

    public final NumberPath<Integer> thirdPrizeBeneficiaryNum = createNumber("thirdPrizeBeneficiaryNum", Integer.class);

    public final QWinNum winNum;

    public QWinInfo(String variable) {
        this(WinInfo.class, forVariable(variable), INITS);
    }

    public QWinInfo(Path<? extends WinInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWinInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWinInfo(PathMetadata metadata, PathInits inits) {
        this(WinInfo.class, metadata, inits);
    }

    public QWinInfo(Class<? extends WinInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.winNum = inits.isInitialized("winNum") ? new QWinNum(forProperty("winNum"), inits.get("winNum")) : null;
    }

}

