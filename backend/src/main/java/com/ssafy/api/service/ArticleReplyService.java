package com.ssafy.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ssafy.api.request.articleReply.ArticleReplyRegisterPostReq;
import com.ssafy.api.request.articleReply.ArticleReplyUpdatePatchReq;
import com.ssafy.db.entity.ArticleReply;

/**
 *	모집 게시판 댓글 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface ArticleReplyService {
	ArticleReply createArticleReply(ArticleReplyRegisterPostReq articleReplyRegisterInfo);

	void deleteArticleReplyById(Long id);

	ArticleReply updateArticleReply(Long id, ArticleReplyUpdatePatchReq updateInfo);

	List<ArticleReply> getArticleReply(Specification<ArticleReply> spec);

	Page<ArticleReply> getArticleReply(Specification<ArticleReply> spec, Pageable pageRequest);
}
