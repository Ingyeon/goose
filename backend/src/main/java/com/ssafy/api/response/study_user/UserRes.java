package com.ssafy.api.response.study_user;

import com.ssafy.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserResponse")
public class UserRes{
	@ApiModelProperty(name="ID")
	Long id;
	@ApiModelProperty(name="User ID")
	String userId;
	@ApiModelProperty(name="이름")
	String name;
	@ApiModelProperty(name="프로필")
	String photo;
	@ApiModelProperty(name="가입일")
	String joinDate;
	@ApiModelProperty(name="이메일")
	String email;
	@ApiModelProperty(name="관심분야")
	String interest;
	
	public static UserRes of(User user) {
		UserRes res = new UserRes();
		res.setId(user.getId());
		res.setUserId(user.getUserId());
		res.setEmail(user.getEmail());
		res.setInterest(user.getInterest());
		res.setJoinDate(user.getJoinDate());
		res.setName(user.getName());
		res.setPhoto(user.getPhoto());
		return res;
	}
}
