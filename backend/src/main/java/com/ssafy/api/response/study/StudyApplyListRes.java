package com.ssafy.api.response.study;

//스터디 신청 멤버 조회
public interface StudyApplyListRes {
	int getUser_pk();
	int getId();
	String getUser_id();
	String getName();
	int getStudy_pk();
	String getApply_date();
	
}
