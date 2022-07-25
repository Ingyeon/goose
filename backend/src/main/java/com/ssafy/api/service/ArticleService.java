package com.ssafy.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ssafy.api.request.article.ArticleRegisterPostReq;
import com.ssafy.api.request.article.ArticleUpdatePatchReq;
import com.ssafy.db.entity.Article;

/**
 *	모집 게시판 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface ArticleService {
	Article createArticle(ArticleRegisterPostReq articleRegisterInfo);
	Article updateArticle(Long id, ArticleUpdatePatchReq articleUpdateInfo);
	Article getArticlesById(Long id);
	void deleteArticlesById(Long id);
	List<Article> getArticles();
	List<Article> getArticles(Specification<Article> spec);
	Page<Article> getArticles(Specification<Article> spec, Pageable pageable);
	
	public int updateHit(Long id);
}