package com.ssafy.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * 모집 게시글 댓글 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class ArticleReplyRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
}
