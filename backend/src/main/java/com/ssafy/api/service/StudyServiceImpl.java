package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.api.request.study.StudyApplyReq;
import com.ssafy.api.request.study.StudyCreatePostReq;
import com.ssafy.api.request.study.StudyMemberSaveReq;
import com.ssafy.api.response.study.StudyApplyListRes;
import com.ssafy.api.response.study.StudyMemberList;
import com.ssafy.api.response.study.StudyMyList;
import com.ssafy.db.entity.Study;
import com.ssafy.db.entity.Study_Apply;
import com.ssafy.db.entity.Study_Member;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.StudyRepository;
import com.ssafy.db.repository.StudyRepositorySupport;
import com.ssafy.db.repository.Study_MemberRepository;
import com.ssafy.db.repository.study_applyRepository;

@Service("studyservice")
public class StudyServiceImpl implements StudyService{
	
	@Autowired
	StudyRepository studyRepository;
	
	@Autowired
	StudyRepositorySupport studyRepositorySupport;
	
	@Autowired
	Study_MemberRepository studyMemberRepository;
	
	@Autowired
	study_applyRepository studyApplyRepository;
	
	

	
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

	@Override
	public int joinStudy(long study_pk, long user_id) {
		
	 	Study study = new Study();
	 	study.setId(study_pk);
	 	
	 	User user = new User();
	 	user.setId(user_id);
	 	Study_Apply studyApply = new Study_Apply();
	 	studyApply.setStudy_pk(study);;
	 	studyApply.setUser_pk(user);
	 	
	 	int joincount = studyApplyRepository.studyJoinChechk(study_pk, user_id);
	 	int membercount = studyApplyRepository.studyMemberChechk(study_pk, user_id);

	 	if(joincount == 0) {
	 		studyApplyRepository.save(studyApply);
	 		return 1; //성공
	 	}
	 	else if (membercount >= 1) {
	 		return 2;	//이미 멤버여서 실패
	 	}
	 	else {
	 		return 0;	//이미신청해서 실패 
	 	}
		
	}

	@Override
	public List<StudyApplyListRes> studyJoinList(long study_pk) {
		List<StudyApplyListRes> joinList = studyApplyRepository.findAllByStudyPk(study_pk);
		return joinList;
	}

	@Override
	public Study_Member insertMember(StudyMemberSaveReq studyMemberSaveReq) {
		Study_Member studyMember = new Study_Member(); 
//		User user = new User();
//		Study study = new Study();
//		
//		user.setId(studyMemberSaveReq.getUser_pk());
//		study.setId(studyMemberSaveReq.getStudy_pk());
//		
//		studyMember.setUser_pk(user);
//		studyMember.setStudy_pk(study);

		studyMemberRepository.save(studyMemberSaveReq.toEntity());
	
		return studyMember;
	}

	@Override
	public void joindelete(long id) {
		Study_Apply studyApply = new Study_Apply();
		studyApply.setId(id);
		studyApplyRepository.delete(studyApply);
	}


	@Override
	public List<StudyMyList> studyListA3(long user_id) {
		List<StudyMyList> studyListA = studyMemberRepository.studyListA3(user_id);
		return studyListA;
	}

	@Override
	public List<StudyMyList> studyList(long user_id) {
		List<StudyMyList> studyList = studyMemberRepository.studyList(user_id);
		return studyList;
	}

	@Override
	public List<StudyMemberList> studyMemberLsit(long study_pk) {
		List<StudyMemberList> joinList = studyMemberRepository.findAllMember(study_pk);
		return joinList;
	}

	@Override
	public int studyMemberOut(long master_id, long user_pk, long study_pk) {
		int mastercheck = studyMemberRepository.studyMemberMasterCheck(master_id, study_pk);
		if(mastercheck > 0) {
			studyMemberRepository.studyMemberOut(user_pk, study_pk);
			return 1;
		}
		else return 0;

	}

	@Override
	public void studyMemberOutMe(long master_id, long user_pk, long study_pk) {
		studyMemberRepository.studyMemberOut(user_pk, study_pk);
		
	}




	
	
	
}
