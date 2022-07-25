package com.ssafy.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ssafy.api.request.article.StudyArticleRegistPostReq;
import com.ssafy.api.request.article.StudyArticleUpdatePatchReq;
import com.ssafy.db.entity.StudyArticle;
import com.ssafy.db.entity.User;

/**
 *	스터디 게시판 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface StudyArticleService {
	StudyArticle createArticle(User user, StudyArticleRegistPostReq articleRegisterInfo);
	StudyArticle updateArticle(Long id, User user, StudyArticleUpdatePatchReq updateInfo);
	StudyArticle getArticlesById(Long id);
	void deleteArticlesById(Long id);
	List<StudyArticle> getArticles();
	List<StudyArticle> getArticles(Specification<StudyArticle> spec);
	Page<StudyArticle> getArticles(Specification<StudyArticle> spec, Pageable pageable);
	
	public int updateHit(Long id);

}