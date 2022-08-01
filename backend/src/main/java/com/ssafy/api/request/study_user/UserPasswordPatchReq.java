package com.ssafy.api.request.study_user;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * 비밀번호 변경 API ([POST] /api/v1/users/pw) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserPasswordPatchRequest")
public class UserPasswordPatchReq {
	String current_password;
	String new_password;
}