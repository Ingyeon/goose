package com.ssafy.api.request.study_user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 회원가입 API ([PATCH] /api/v1/users/{userId}) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserUpdatePatchRequest")
public class UserUpdatePatchReq {
	@ApiModelProperty(name="유저 Name", example="(String) 이름")
	String name;
	@ApiModelProperty(name="유저 Email", example="(String) 이메일")
	String email;
	@ApiModelProperty(name="유저 프로필 사진", example="(String) 사진 사용X")
	String photo;
	@ApiModelProperty(name="유저 Interest", example="(String) 관심")
	String interest;
	@ApiModelProperty(name="유저 Info", example="(String) 정보")
	String info;
}
