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
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.db.entity.Article;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ArticleRepository;

/**
 *	모집 게시판 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	ArticleRepository articleRepository;

	@Override
	public Article createArticle(User user, ArticleRegisterPostReq articleRegisterInfo) {
		Article article = new Article();
		article.setCategory(articleRegisterInfo.getCategory());
		article.setUser_pk(user.getId());
		article.setStudy_pk(articleRegisterInfo.getStudy_pk());
		article.setContent(articleRegisterInfo.getContent());
		article.setTitle(articleRegisterInfo.getTitle());
		article.setState(articleRegisterInfo.getState());
		article.setRecruitment(articleRegisterInfo.getRecruitment());
		article.setName(user.getName());
		
		return articleRepository.save(article);
	}

	@Override
	public Article getArticlesById(Long id) {
		Article article = articleRepository.getOne(id);
		return article;
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
	public Article updateArticle(Long id, User user, ArticleUpdatePatchReq articleUpdateInfo) {
		Article article = articleRepository.getOne(id);
		article.setCategory(articleUpdateInfo.getCategory());
		article.setContent(articleUpdateInfo.getContent());
		article.setTitle(articleUpdateInfo.getTitle());
		article.setState(articleUpdateInfo.getState());
		article.setRecruitment(articleUpdateInfo.getRecruitment());
		article.setName(user.getName());
		return articleRepository.save(article);
	}

	@Override
	@Transactional
	public int updateHit(Long id) {
		return  articleRepository.updateHit(id);
	}

}
