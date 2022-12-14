package com.ssafy.api.controller;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.study_user.UserFindPasswordPatchReq;
import com.ssafy.api.request.study_user.UserLoginPostReq;
import com.ssafy.api.request.study_user.UserPasswordPatchReq;
import com.ssafy.api.request.study_user.UserRegisterPostReq;
import com.ssafy.api.request.study_user.UserUpdatePatchReq;
import com.ssafy.api.response.study_user.UserInfoRes;
import com.ssafy.api.response.study_user.UserLoginPostRes;
import com.ssafy.api.response.study_user.UserRes;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepositorySupport;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping()
	@ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> registerUser(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) UserRegisterPostReq registerInfo) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		User user = userService.createUser(registerInfo);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@GetMapping("{userId}")
	@ApiOperation(value = "회원 정보 조회", notes = "아이디로 회원의 정보를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserInfoRes> getUser(@RequestParam(value = "userId")String userId) {
		
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		User user = userService.getUserByUserId(userId);
		return ResponseEntity.status(200).body(UserInfoRes.of(user));
	}
	
	@GetMapping("/email")
	@ApiOperation(value = "이메일 중복 확인", notes = "이메일 중복 여부를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<String> checkEmail(@RequestParam(value = "email")String email) {
		
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		if( !userService.getUserByEmail(email)) return ResponseEntity.status(200).body("OK");
		else return ResponseEntity.status(200).body("Duplicated");

	}
	
	@GetMapping("/findID")
	@ApiOperation(value = "ID 찾기", notes = "이름, 이메일로 ID 찾기") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<String> findID(@RequestParam(value = "name")String name,@RequestParam(value = "email")String email) {
		String result = userService.getUserByNameAndEmail(name, email);
		if( result !=null) return ResponseEntity.status(200).body(result);
		 else return ResponseEntity.status(404).body("Wrong Info");
	}
	
	@GetMapping("/findpw")
	@ApiOperation(value = "PW 찾기1", notes = "아이디, 이메일로 PW 찾기") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<String> findPW(@RequestParam(value = "userId")String userId,@RequestParam(value = "email")String email) {
			
		if( userService.getUserByUserIdAndEmail(userId, email)) return ResponseEntity.status(200).body("OK");
		else return ResponseEntity.status(404).body("Wrong Info");
	}
	
	@PatchMapping("/findpw")
	@ApiOperation(value = "PW 찾기2", notes = "<strong>pw</strong>를 수정 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> updatePw(
			@RequestBody @ApiParam(value="회원 PW 정보", required = true) UserFindPasswordPatchReq updateInfo) {
			System.out.println(updateInfo.getNew_password() +" "+updateInfo.getUser_id());
			userService.updatePW(updateInfo.getNew_password(), updateInfo.getUser_id());
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@PatchMapping()
	@ApiOperation(value = "회원 정보 수정", notes = "<strong>회원정보</strong>를 수정 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> updateUser(
			@RequestBody @ApiParam(value="회원 수정 정보", required = true) UserUpdatePatchReq updateInfo, @ApiIgnore Authentication authentication) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String userId = userDetails.getUsername();
		User user = userService.updateUser(updateInfo, userId);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@PatchMapping("/pw")
	@ApiOperation(value = "회원 PW 수정", notes = "<strong>회원 PW</strong>를 수정 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "PW 불일치"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> updateUser(
			@RequestBody @ApiParam(value="회원 PW 정보", required = true) UserPasswordPatchReq updateInfo, @ApiIgnore Authentication authentication) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		
		if(passwordEncoder.matches(updateInfo.getCurrent_password(), userDetails.getPassword())) {	
			String userId = userDetails.getUsername();
			userService.updatePW(updateInfo.getNew_password(), userId);
			
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else return ResponseEntity.status(404).body(BaseResponseBody.of(404, "Invalid Password"));

	}
	
	@DeleteMapping()
	@ApiOperation(value = "회원 탈퇴", notes = "<strong>아이디</strong>를 통해 회원탈퇴 한다.") 
    @ApiResponses({
        @ApiResponse(code = 204, message = "성공"),
    })
	public ResponseEntity<? extends BaseResponseBody> deleteUser(
			@RequestHeader @ApiParam(value="회원 비밀번호", required = true) String password, @ApiIgnore Authentication authentication) {
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();

		
		if(passwordEncoder.matches(password, userDetails.getPassword())) {	
			String userId = userDetails.getUsername();
			userService.deleteUserByUserId(userId);
			return ResponseEntity.status(204).body(BaseResponseBody.of(204, "Success"));
		} else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Invalid Password"));
		
	}
	
	@GetMapping("/me")
	@ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserRes> getMyInfo(@ApiIgnore Authentication authentication) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
		
		return ResponseEntity.status(200).body(UserRes.of(user));
	}
}
