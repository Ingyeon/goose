package com.ssafy.api.request.study;

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
	
	@ApiModelProperty(name="멤버 권한레벨", example="")
	int authority;
	
	@ApiModelProperty(name="출석여부", example="")
	int attendance;
}
