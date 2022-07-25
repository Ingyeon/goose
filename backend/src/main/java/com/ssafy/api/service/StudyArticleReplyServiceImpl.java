package com.ssafy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.articleReply.ArticleReplyUpdatePatchReq;
import com.ssafy.api.request.articleReply.StudyArticleReplyRegisterPostReq;
import com.ssafy.db.entity.StudyArticleReply;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.StudyArticleReplyRepository;

/**
 *	스터디 게시판 댓글 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("studyArticleReplyService")
public class StudyArticleReplyServiceImpl implements StudyArticleReplyService{
	@Autowired
	StudyArticleReplyRepository studyArticleReplyRepository;

	@Override
	public StudyArticleReply createArticleReply(User user, StudyArticleReplyRegisterPostReq articleReplyRegisterInfo) {
		StudyArticleReply articleReply = new StudyArticleReply();
		articleReply.setUser_pk(user.getId());
		articleReply.setName(user.getName());
		
		articleReply.setArticle_pk(articleReplyRegisterInfo.getArticle_pk());
		articleReply.setStudy_pk(articleReplyRegisterInfo.getStudy_pk());
		articleReply.setRe_content(articleReplyRegisterInfo.getRe_content());
		
		return studyArticleReplyRepository.save(articleReply);
	}

	@Override
	public void deleteArticleReplyById(Long id) {
		studyArticleReplyRepository.deleteById(id);
	}

	@Override
	public StudyArticleReply updateArticleReply(Long id, User user, ArticleReplyUpdatePatchReq updateInfo) {
		StudyArticleReply articleReply = studyArticleReplyRepository.getOne(id);
		articleReply.setName(user.getName());
		articleReply.setRe_content(updateInfo.getRe_content());
		return studyArticleReplyRepository.save(articleReply);
	}

	@Override
	public Page<StudyArticleReply> getArticleReply(Specification<StudyArticleReply> spec, Pageable pageRequest) {
		Page<StudyArticleReply> articleReplys = studyArticleReplyRepository.findAll(spec, pageRequest);
		return articleReplys;
	}

	
	

	
}
