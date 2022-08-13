package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudyArticle is a Querydsl query type for StudyArticle
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyArticle extends EntityPathBase<StudyArticle> {

    private static final long serialVersionUID = 99244975L;

    public static final QStudyArticle studyArticle = new QStudyArticle("studyArticle");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> article_pk = createNumber("article_pk", Long.class);

    public final StringPath category = createString("category");

    public final StringPath content = createString("content");

    public final StringPath date = createString("date");

    public final NumberPath<Integer> hit = createNumber("hit", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath image = createString("image");

    public final StringPath name = createString("name");

    public final NumberPath<Long> study_pk = createNumber("study_pk", Long.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> user_pk = createNumber("user_pk", Long.class);

    public QStudyArticle(String variable) {
        super(StudyArticle.class, forVariable(variable));
    }

    public QStudyArticle(Path<? extends StudyArticle> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyArticle(PathMetadata metadata) {
        super(StudyArticle.class, metadata);
    }

}

