package com.ssafy.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.study_user.UserRegisterPostReq;
import com.ssafy.api.request.study_user.UserUpdatePatchReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRepositorySupport userRepositorySupport;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();
		user.setUserId(userRegisterInfo.getId());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		user.setName(userRegisterInfo.getName());
		user.setEmail(userRegisterInfo.getEmail());
		user.setInterest(userRegisterInfo.getInterest());
		user.setPhoto(userRegisterInfo.getPhoto());

		return userRepository.save(user);
	}
	
	@Override
	public User updateUser(UserUpdatePatchReq updateInfo, String userId) {
		User user = userRepositorySupport.findUserByUserId(userId).get();

		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
//		user.setPassword(passwordEncoder.encode(updateInfo.getPassword()));
		user.setName(updateInfo.getName());
		user.setEmail(updateInfo.getEmail());
		user.setInterest(updateInfo.getInterest());
		user.setPhoto(updateInfo.getPhoto());
		user.setInfo(updateInfo.getInfo());
		
		return userRepository.save(user);
	}
	
	@Override
	public void deleteUserByUserId(String userId) {
		userRepository.deleteUserByUserId(userId);
	}

	@Override
	public User getUserByUserId(String userId) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		User user = userRepositorySupport.findUserByUserId(userId).get();
		return user;
	}


	@Override
	public String findName(Long id) {
		return userRepository.findById(id).get().getName();
	}

	@Override
	public User updatePW(String newPw, String userId) {
		User user = userRepositorySupport.findUserByUserId(userId).get();

		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(newPw));
		return userRepository.save(user);
	}

	@Override
	public Boolean getUserByEmail(String email) {

		if(userRepositorySupport.findUserByEmail(email).isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public String getUserByNameAndEmail(String name, String email) {
		Optional<User> user = userRepositorySupport.findUserByNameAndEmail(name, email);
		if(user.isPresent()) {
			return user.get().getUserId();
		}
		return null;
	}

	@Override
	public Boolean getUserByUserIdAndEmail(String userId, String email) {
		Optional<User> user = userRepositorySupport.findUserByUserIdAndEmail(userId, email);
		if(user.isPresent()) {
			return true;
		}
		return false;
	}
}
