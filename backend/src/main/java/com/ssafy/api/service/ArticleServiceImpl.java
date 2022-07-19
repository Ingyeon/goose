package com.ssafy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.ArticleRegisterPostReq;
import com.ssafy.db.entity.Article;
import com.ssafy.db.repository.ArticleRepository;
import com.ssafy.db.repository.ArticleRepositorySupport;

/**
 *	게시글 비즈니스 로직 처리를 위한 서비스 구현 정의.
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
		return articleRepository.save(article);
	}
	
	

}
