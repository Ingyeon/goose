package com.ssafy.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.articleReply.ArticleReplyRegisterPostReq;
import com.ssafy.api.request.articleReply.ArticleReplyUpdatePatchReq;
import com.ssafy.db.entity.ArticleReply;
import com.ssafy.db.repository.ArticleReplyRepository;
import com.ssafy.db.repository.ArticleReplyRepositorySupport;

/**
 *	모집 게시판 댓글 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("articleReplyService")
public class ArticleReplyServiceImpl implements ArticleReplyService {
	@Autowired
	ArticleReplyRepository articleReplyRepository;
	
	@Autowired
	ArticleReplyRepositorySupport articleReplyRepositorySupport;

	@Override
	public ArticleReply createArticleReply(ArticleReplyRegisterPostReq articleReplyRegisterInfo) {
		ArticleReply articleReply = new ArticleReply();
		articleReply.setArticle_pk(articleReplyRegisterInfo.getArticle_pk());
		articleReply.setUser_pk(articleReplyRegisterInfo.getUser_pk());
		articleReply.setRe_content(articleReplyRegisterInfo.getRe_content());
		
		return articleReplyRepository.save(articleReply);
	}
	
	@Override
	public List<ArticleReply> getArticleReply(Specification<ArticleReply> spec) {
		List<ArticleReply> articleReplys = articleReplyRepository.findAll(spec);
		return articleReplys;
	}
	
	@Override
	public Page<ArticleReply> getArticleReply(Specification<ArticleReply> spec, Pageable pageRequest) {
		Page<ArticleReply> articleReplys = articleReplyRepository.findAll(spec, pageRequest);
		return articleReplys;
	}
	
	@Override
	public ArticleReply updateArticleReply(Long id, ArticleReplyUpdatePatchReq updateInfo) {
		ArticleReply articleReply = articleReplyRepository.getOne(id);
		articleReply.setRe_content(updateInfo.getRe_content());
		return articleReplyRepository.save(articleReply);
	}

	@Override
	public void deleteArticleReplyById(Long id) {
		articleReplyRepository.deleteById(id);
	}







}
