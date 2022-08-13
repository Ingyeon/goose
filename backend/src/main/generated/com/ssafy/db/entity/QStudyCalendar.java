package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudyCalendar is a Querydsl query type for StudyCalendar
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyCalendar extends EntityPathBase<StudyCalendar> {

    private static final long serialVersionUID = -167820379L;

    public static final QStudyCalendar studyCalendar = new QStudyCalendar("studyCalendar");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath backgroundColor = createString("backgroundColor");

    public final StringPath borderColor = createString("borderColor");

    public final StringPath content = createString("content");

    public final StringPath end = createString("end");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath start = createString("start");

    public final NumberPath<Long> study_pk = createNumber("study_pk", Long.class);

    public final StringPath textColor = createString("textColor");

    public final StringPath title = createString("title");

    public final NumberPath<Long> user_pk = createNumber("user_pk", Long.class);

    public final StringPath writer = createString("writer");

    public QStudyCalendar(String variable) {
        super(StudyCalendar.class, forVariable(variable));
    }

    public QStudyCalendar(Path<? extends StudyCalendar> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyCalendar(PathMetadata metadata) {
        super(StudyCalendar.class, metadata);
    }

}

