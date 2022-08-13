package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudy is a Querydsl query type for Study
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudy extends EntityPathBase<Study> {

    private static final long serialVersionUID = 471210823L;

    public static final QStudy study = new QStudy("study");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath category = createString("category");

    public final StringPath date = createString("date");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath image = createString("image");

    public final NumberPath<Integer> maxmember = createNumber("maxmember", Integer.class);

    public final NumberPath<Integer> member = createNumber("member", Integer.class);

    public final NumberPath<Integer> open = createNumber("open", Integer.class);

    public final StringPath password = createString("password");

    public final StringPath title = createString("title");

    public final StringPath url_conf = createString("url_conf");

    public final StringPath url_page = createString("url_page");

    public QStudy(String variable) {
        super(Study.class, forVariable(variable));
    }

    public QStudy(Path<? extends Study> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudy(PathMetadata metadata) {
        super(Study.class, metadata);
    }

}

