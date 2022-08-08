package com.ssafy.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.article.StudyArticleRegistPostReq;
import com.ssafy.api.request.article.StudyArticleUpdatePatchReq;
import com.ssafy.db.entity.StudyArticle;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.StudyArticleRepository;

/**
 *	스터디 게시판 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("studyArticleService")
public class StudyArticleServiceImpl implements StudyArticleService {
	
	@Autowired
	StudyArticleRepository studyArticleRepository;

	@Override
	public StudyArticle createArticle(User user, StudyArticleRegistPostReq articleRegisterInfo) {
		StudyArticle studyArticle = new StudyArticle();
		studyArticle.setUser_pk(user.getId());
		studyArticle.setName(user.getName());
		
		studyArticle.setCategory(articleRegisterInfo.getCategory());
		studyArticle.setStudy_pk(articleRegisterInfo.getStudy_pk());
		studyArticle.setContent(articleRegisterInfo.getContent());
		studyArticle.setTitle(articleRegisterInfo.getTitle());
		studyArticle.setImage(articleRegisterInfo.getImage());
	
		return studyArticleRepository.save(studyArticle);
	}

	@Override
	public StudyArticle updateArticle(Long id, User user, StudyArticleUpdatePatchReq articleUpdateInfo) {
		StudyArticle studyArticle = studyArticleRepository.getOne(id);
		studyArticle.setName(user.getName());
		studyArticle.setCategory(articleUpdateInfo.getCategory());
		studyArticle.setContent(articleUpdateInfo.getContent());
		studyArticle.setTitle(articleUpdateInfo.getTitle());
		studyArticle.setImage(articleUpdateInfo.getImage());
		return studyArticleRepository.save(studyArticle);
	}

	@Override
	public StudyArticle getArticlesById(Long id) {
		StudyArticle studyArticle = studyArticleRepository.getOne(id);
		return studyArticle;
	}

	@Override
	public void deleteArticlesById(Long id) {
		studyArticleRepository.deleteById(id);
		
	}

	@Override
	public List<StudyArticle> getArticles() {
		List<StudyArticle> articles =studyArticleRepository.findAll();
		return articles;
	}

	@Override
	public List<StudyArticle> getArticles(Specification<StudyArticle> spec) {
		List<StudyArticle> articles =studyArticleRepository.findAll(spec);
		return articles;
	}

	@Override
	public Page<StudyArticle> getArticles(Specification<StudyArticle> spec, Pageable pageable) {
		Page<StudyArticle> studyArticles = studyArticleRepository.findAll(spec, pageable);
		return studyArticles;
	}

	@Override
	@Transactional
	public int updateHit(Long id) {
		return studyArticleRepository.updateHit(id);
	}
 

}
