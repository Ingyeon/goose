package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudy_Member is a Querydsl query type for Study_Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudy_Member extends EntityPathBase<Study_Member> {

    private static final long serialVersionUID = -118461774L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudy_Member study_Member = new QStudy_Member("study_Member");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> attendance = createNumber("attendance", Integer.class);

    public final NumberPath<Integer> authority = createNumber("authority", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QStudy study_pk;

    public final QUser user_pk;

    public QStudy_Member(String variable) {
        this(Study_Member.class, forVariable(variable), INITS);
    }

    public QStudy_Member(Path<? extends Study_Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudy_Member(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudy_Member(PathMetadata metadata, PathInits inits) {
        this(Study_Member.class, metadata, inits);
    }

    public QStudy_Member(Class<? extends Study_Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.study_pk = inits.isInitialized("study_pk") ? new QStudy(forProperty("study_pk")) : null;
        this.user_pk = inits.isInitialized("user_pk") ? new QUser(forProperty("user_pk")) : null;
    }

}

