package com.ssafy.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class StudyRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
}
