package com.ssafy.api.request.study_user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterPostReq {
	@ApiModelProperty(name="유저 ID", example="(String) 로그인 아이디")
	String id;
	@ApiModelProperty(name="유저 Password", example="(String) 패스워드")
	String password;
	@ApiModelProperty(name="유저 Name", example="(String) 이름")
	String name;
	@ApiModelProperty(name="유저 Email", example="(String) 이메일")
	String email;
	@ApiModelProperty(name="유저 프로필 사진", example="(String) 사진 사용X")
	String photo;
	@ApiModelProperty(name="유저 Interest", example="(String) 관심")
	String interest;
}
