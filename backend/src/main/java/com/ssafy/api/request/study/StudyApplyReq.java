package com.ssafy.api.request.study;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyApplyReq {
	@ApiModelProperty(name="신청자 유저 번호", example="")
	long user_pk;
	
	@ApiModelProperty(name="스터디 번호", example="")
	long study_pk;
	
	
}
