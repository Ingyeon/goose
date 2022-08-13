package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QArticleReply is a Querydsl query type for ArticleReply
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QArticleReply extends EntityPathBase<ArticleReply> {

    private static final long serialVersionUID = 535980118L;

    public static final QArticleReply articleReply = new QArticleReply("articleReply");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> article_pk = createNumber("article_pk", Long.class);

    public final StringPath date = createString("date");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final StringPath re_content = createString("re_content");

    public final NumberPath<Long> user_pk = createNumber("user_pk", Long.class);

    public QArticleReply(String variable) {
        super(ArticleReply.class, forVariable(variable));
    }

    public QArticleReply(Path<? extends ArticleReply> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArticleReply(PathMetadata metadata) {
        super(ArticleReply.class, metadata);
    }

}

