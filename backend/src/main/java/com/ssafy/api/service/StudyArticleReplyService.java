package com.ssafy.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ssafy.api.request.articleReply.ArticleReplyUpdatePatchReq;
import com.ssafy.api.request.articleReply.StudyArticleReplyRegisterPostReq;
import com.ssafy.db.entity.StudyArticleReply;
import com.ssafy.db.entity.User;

/**
 *	스터디 게시판 댓글 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface StudyArticleReplyService {
	StudyArticleReply createArticleReply(User user, StudyArticleReplyRegisterPostReq articleReplyRegisterInfo);

	void deleteArticleReplyById(Long id);

	StudyArticleReply updateArticleReply(Long id, User user, ArticleReplyUpdatePatchReq updateInfo);

	Page<StudyArticleReply> getArticleReply(Specification<StudyArticleReply> spec, Pageable pageRequest);
}
