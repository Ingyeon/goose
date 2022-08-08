package com.ssafy.api.request.study_user;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * 비밀번호 변경 API ([Patch] /api/v1/users/findpw) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserFindPasswordPatchRequest")
public class UserFindPasswordPatchReq {
		String user_id;
		String new_password;
}