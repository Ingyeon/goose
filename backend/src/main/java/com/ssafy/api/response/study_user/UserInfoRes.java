package com.ssafy.api.response.study_user;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 정보 조회 API ([GET] /api/v1/users/{userId}) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserInfoResponse")
public class UserInfoRes {
	@ApiModelProperty(name="User ID")
	String userId;
	@ApiModelProperty(name="Email")
	String email;
	@ApiModelProperty(name="Name")
	String name;
	
	public static UserInfoRes of(User user) {
		UserInfoRes res = new UserInfoRes();
		res.setUserId(user.getUserId());
		res.setEmail(user.getEmail());
		res.setName(user.getName());
		return res;
	}
}
