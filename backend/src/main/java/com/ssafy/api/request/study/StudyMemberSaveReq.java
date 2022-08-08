package com.ssafy.api.request.study;

import com.ssafy.db.entity.Study;
import com.ssafy.db.entity.Study_Member;
import com.ssafy.db.entity.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyMemberSaveReq {
	@ApiModelProperty(name="스터디멤버번호", example="")
	long id;
	
	@ApiModelProperty(name="유저 번호", example="")
	long user_pk;
	
	@ApiModelProperty(name="스터디 번호", example="")
	long study_pk;
	
	@ApiModelProperty(name="멤버 권한레벨", example="신청 승인에서 삭제")
	int authority;
	
	@ApiModelProperty(name="출석여부", example="신청 승인에서 삭제")
	int attendance;
	
	@ApiModelProperty(name="가입날짜", example="신청 승인에서 삭제")
	String study_time;

	public Study_Member toEntity() { 
		User user = new User();
		user.setId(user_pk);
		Study study = new Study();
		study.setId(study_pk);

		
		return Study_Member.builder()
				.user_pk(user)
				.study_pk(study)
				.build();
	}
	
	
}
