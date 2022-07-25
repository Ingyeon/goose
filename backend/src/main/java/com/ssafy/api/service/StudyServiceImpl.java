package com.ssafy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.api.request.study.StudyCreatePostReq;
import com.ssafy.db.entity.Study;
import com.ssafy.db.entity.Study_Member;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.StudyRepository;
import com.ssafy.db.repository.Study_MemberRepository;

@Service("studyservice")
public class StudyServiceImpl implements StudyService{
	
	@Autowired
	StudyRepository studyRepository;
		
	@Autowired
	Study_MemberRepository studyMemberRepository;

	@Override
	public Study createStudy(StudyCreatePostReq studyCreatePostReq) {
		
		// 스터디 생성
		Study study = new Study();
		study.setTitle(studyCreatePostReq.getTitle());
		study.setUrl_page(studyCreatePostReq.getUrl_page());
		study.setUrl_conf(studyCreatePostReq.getUrl_conf());
		study.setCategory(studyCreatePostReq.getCategory());
		study.setImage(studyCreatePostReq.getImage());
		study.setOpen(studyCreatePostReq.getOpen());
		study.setMember(1); // 개설시 반장한명
		study.setMaxmember(studyCreatePostReq.getMaxmember());
		study.setPassword(studyCreatePostReq.getPassword());
		studyRepository.save(study);
		
		User user = new User();
		user.setId(studyCreatePostReq.getId());
		
		Study_Member studyMember = new Study_Member();
		
		studyMember.setUser_pk(user);
		studyMember.setStudy_pk(study);
		studyMember.setAuthority(5);
		studyMemberRepository.save(studyMember);
		
		return study;
	}

	/** 스터디를 삭제하는 deleteStudy 메소드 */
	@Override
	public void deleteStudy(long id) {
		Study study = new Study();
		study.setId(id);
		studyRepository.delete(study);
	}
	
	@Override
	public Study findById(Long id) {
		Study study = studyRepository.findById(id).get();
		return study;
	}

	@Transactional
	@Override
	public Study updateStudy(Study study, StudyCreatePostReq studyUpdateInfo) {
		study.updateStudy(studyUpdateInfo);
		return study;
	}

	
	
}
