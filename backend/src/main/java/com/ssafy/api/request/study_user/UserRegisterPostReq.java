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
<<<<<<< HEAD
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
=======
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
>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)
	String interest;
}
