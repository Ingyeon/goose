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
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ArticleReplyRepository;

/**
 *	모집 게시판 댓글 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("articleReplyService")
public class ArticleReplyServiceImpl implements ArticleReplyService {
	@Autowired
	ArticleReplyRepository articleReplyRepository;
	

	@Override
	public ArticleReply createArticleReply(User user, ArticleReplyRegisterPostReq articleReplyRegisterInfo) {
		ArticleReply articleReply = new ArticleReply();
		articleReply.setUser_pk(user.getId());
		articleReply.setName(user.getName());
		
		articleReply.setArticle_pk(articleReplyRegisterInfo.getArticle_pk());
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
	public ArticleReply updateArticleReply(Long id, User user, ArticleReplyUpdatePatchReq updateInfo) {
		ArticleReply articleReply = articleReplyRepository.getOne(id);
		articleReply.setName(user.getName());
		articleReply.setRe_content(updateInfo.getRe_content());
		return articleReplyRepository.save(articleReply);
	}

	@Override
	public void deleteArticleReplyById(Long id) {
		articleReplyRepository.deleteById(id);
	}

}
