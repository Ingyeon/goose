package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudyArticleReply is a Querydsl query type for StudyArticleReply
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyArticleReply extends EntityPathBase<StudyArticleReply> {

    private static final long serialVersionUID = -1800815493L;

    public static final QStudyArticleReply studyArticleReply = new QStudyArticleReply("studyArticleReply");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> article_pk = createNumber("article_pk", Long.class);

    public final StringPath date = createString("date");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final StringPath re_content = createString("re_content");

    public final NumberPath<Long> study_pk = createNumber("study_pk", Long.class);

    public final NumberPath<Long> user_pk = createNumber("user_pk", Long.class);

    public QStudyArticleReply(String variable) {
        super(StudyArticleReply.class, forVariable(variable));
    }

    public QStudyArticleReply(Path<? extends StudyArticleReply> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyArticleReply(PathMetadata metadata) {
        super(StudyArticleReply.class, metadata);
    }

}

