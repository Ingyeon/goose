package com.ssafy.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ssafy.api.request.article.ArticleRegisterPostReq;
import com.ssafy.api.request.article.ArticleUpdatePatchReq;
import com.ssafy.db.entity.Article;
import com.ssafy.db.entity.User;

/**
 *	모집 게시판 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface ArticleService {
	Article createArticle(User user, ArticleRegisterPostReq articleRegisterInfo);
	Article updateArticle(Long id, User user, ArticleUpdatePatchReq articleUpdateInfo);
	Article getArticlesById(Long id);
	void deleteArticlesById(Long id);
	Page<Article> getArticles(Specification<Article> spec, Pageable pageable);
	
	public int updateHit(Long id);
	
}