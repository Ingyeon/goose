package com.ssafy.db.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.ArticleReply;

/**
 * 모집 게시글 댓글 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface ArticleReplyRepository extends JpaRepository<ArticleReply, Long>, JpaSpecificationExecutor<ArticleReply>{
	List<ArticleReply> findAll(Specification<ArticleReply> spec);
	Page<ArticleReply> findAll(Specification<ArticleReply> spec, Pageable pageable);
}