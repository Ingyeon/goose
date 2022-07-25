package com.ssafy.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.api.request.article.ArticleRegisterPostReq;
import com.ssafy.api.request.article.ArticleUpdatePatchReq;
import com.ssafy.db.entity.Article;
import com.ssafy.db.repository.ArticleRepository;
import com.ssafy.db.repository.ArticleRepositorySupport;

/**
 *	모집 게시판 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	ArticleRepositorySupport articleRepositorySupport;

	@Override
	public Article createArticle(ArticleRegisterPostReq articleRegisterInfo) {
		Article article = new Article();
		article.setCategory(articleRegisterInfo.getCategory());
		article.setUser_pk(articleRegisterInfo.getUser_pk());
		article.setStudy_pk(articleRegisterInfo.getStudy_pk());
		article.setContent(articleRegisterInfo.getContent());
		article.setTitle(articleRegisterInfo.getTitle());
		article.setState(articleRegisterInfo.getState());
		article.setRecruitment(articleRegisterInfo.getRecruitment());
		
		return articleRepository.save(article);
	}

	@Override
	public Article getArticlesById(Long id) {
		Article article = articleRepository.getOne(id);
		return article;
	}
	
	@Override
	public List<Article> getArticles() {
		List<Article> articles = articleRepository.findAll();
		return articles;
	}
	
	@Override
	public List<Article> getArticles(Specification<Article> spec) {
		List<Article> articles = articleRepository.findAll(spec);
		return articles;
	}
	
	@Override
	public Page<Article> getArticles(Specification<Article> spec, Pageable pageable) {
		Page<Article> articles = articleRepository.findAll(spec, pageable);
		return articles;
	}

	@Override
	public void deleteArticlesById(Long id) {
		articleRepository.deleteById(id);
	}

	@Override
	public Article updateArticle(Long id, ArticleUpdatePatchReq articleUpdateInfo) {
		Article article = articleRepository.getOne(id);
		article.setCategory(articleUpdateInfo.getCategory());
		article.setContent(articleUpdateInfo.getContent());
		article.setTitle(articleUpdateInfo.getTitle());
		article.setState(articleUpdateInfo.getState());
		article.setRecruitment(articleUpdateInfo.getRecruitment());
		return articleRepository.save(article);
	}

	@Override
	@Transactional
	public int updateHit(Long id) {
		return  articleRepository.updateHit(id);
	}

}
