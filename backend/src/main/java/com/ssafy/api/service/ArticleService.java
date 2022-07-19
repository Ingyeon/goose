package com.ssafy.api.service;

import com.ssafy.api.request.ArticleRegisterPostReq;
import com.ssafy.db.entity.Article;

/**
 *	게시판 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface ArticleService {
	Article createArticle(ArticleRegisterPostReq articleRegisterInfo);
}