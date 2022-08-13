package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QArticle is a Querydsl query type for Article
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QArticle extends EntityPathBase<Article> {

    private static final long serialVersionUID = -1286180108L;

    public static final QArticle article = new QArticle("article");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath category = createString("category");

    public final StringPath content = createString("content");

    public final StringPath date = createString("date");

    public final NumberPath<Integer> hit = createNumber("hit", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath image = createString("image");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> recruitment = createNumber("recruitment", Integer.class);

    public final StringPath state = createString("state");

    public final NumberPath<Long> study_pk = createNumber("study_pk", Long.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> user_pk = createNumber("user_pk", Long.class);

    public QArticle(String variable) {
        super(Article.class, forVariable(variable));
    }

    public QArticle(Path<? extends Article> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArticle(PathMetadata metadata) {
        super(Article.class, metadata);
    }

}

