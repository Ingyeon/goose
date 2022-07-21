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
	@ApiModelProperty(name="유저 ID", example="ssafy_web")
	String id;
	@ApiModelProperty(name="유저 Password", example="your_password")
	String password;
	@ApiModelProperty(name="유저 Name", example="your_name")
	String name;
	@ApiModelProperty(name="유저 가입일", example="your_joinDate")
	String joinDate;
	@ApiModelProperty(name="유저 Email", example="your_email")
	String email;
	@ApiModelProperty(name="유저 프로필 사진", example="your_photo")
	String photo;
	@ApiModelProperty(name="유저 Authority", example="your_authority")
	int authority;
	@ApiModelProperty(name="유저 Interest", example="your_interest")
	String interest;
}
