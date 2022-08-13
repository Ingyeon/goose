package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudy_Apply is a Querydsl query type for Study_Apply
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudy_Apply extends EntityPathBase<Study_Apply> {

    private static final long serialVersionUID = -845856682L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudy_Apply study_Apply = new QStudy_Apply("study_Apply");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath apply_date = createString("apply_date");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QStudy study_pk;

    public final QUser user_pk;

    public QStudy_Apply(String variable) {
        this(Study_Apply.class, forVariable(variable), INITS);
    }

    public QStudy_Apply(Path<? extends Study_Apply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudy_Apply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudy_Apply(PathMetadata metadata, PathInits inits) {
        this(Study_Apply.class, metadata, inits);
    }

    public QStudy_Apply(Class<? extends Study_Apply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.study_pk = inits.isInitialized("study_pk") ? new QStudy(forProperty("study_pk")) : null;
        this.user_pk = inits.isInitialized("user_pk") ? new QUser(forProperty("user_pk")) : null;
    }

}

