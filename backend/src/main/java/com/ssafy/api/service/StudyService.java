package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.request.study.StudyCreatePostReq;
import com.ssafy.api.request.study.StudyMemberSaveReq;
import com.ssafy.api.response.study.StudyApplyListRes;
import com.ssafy.api.response.study.StudyMemberList;
import com.ssafy.db.entity.Study;
import com.ssafy.db.entity.Study_Member;

public interface StudyService {
	public Study findById(Long id);
	Study createStudy(StudyCreatePostReq studyCreatePostReq);
	void deleteStudy(long id);
	public Study updateStudy(Study study, StudyCreatePostReq studyUpdateInfo);
	public int joinStudy(long study_pk, long user_pk);
	public List<StudyApplyListRes> studyJoinList(long study_pk);
	public Study_Member insertMember(StudyMemberSaveReq studyMemberSaveReq);
	public void joindelete(long id);
	public List<String> studyListA3(long user_id);
	public List<String> studyList(long user_id);
	public List<StudyMemberList> studyMemberLsit(long study_pk);

	
}
