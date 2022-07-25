package com.ssafy.api.service;

import com.ssafy.api.request.study_user.UserRegisterPostReq;
import com.ssafy.api.request.study_user.UserUpdatePatchReq;
import com.ssafy.db.entity.User;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	User createUser(UserRegisterPostReq userRegisterInfo);
	User updateUser(UserUpdatePatchReq updateInfo, String userId);
	User getUserByUserId(String userId);
	void deleteUserByUserId(String userId);
	String findName(Long id);

}
