package com.ssafy.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.study.StudyCreatePostReq;
import com.ssafy.api.request.study.StudyMemberSaveReq;
import com.ssafy.api.response.study.StudyApplyListRes;
import com.ssafy.api.response.study.StudyMemberList;
import com.ssafy.api.response.study.StudyMyList;
import com.ssafy.api.service.StudyService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Study;
import com.ssafy.db.entity.Study_Apply;
import com.ssafy.db.entity.Study_Member;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "STUDY API", tags = {"Study"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/study")
public class StudyController {
	public static final Logger logger = LoggerFactory.getLogger(StudyController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";


	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired 
	StudyService studyService;
	
	@PostMapping("/create")
	@ApiOperation(value = "스터디 생성 (token)", notes = "스터디 정보를 입력하고 생성.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "스터디 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<? extends BaseResponseBody> createStudy(
			@RequestBody @ApiParam(value="스터디개설 정보", required = true) StudyCreatePostReq studyCreatePosetReq, @ApiIgnore Authentication authentication ){
		
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();  
		long user_id = userDetails.getUserId();
		studyCreatePosetReq.setId(user_id);
		studyService.createStudy(studyCreatePosetReq);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	
	@DeleteMapping("/remove/{id}")
	@ApiOperation(value = "스터디방 삭제", notes = "스터디방 삭제.") 
    @ApiResponses({
        @ApiResponse(code = 204, message = "성공"),
    })
	public ResponseEntity<String> deleteStudy(@PathVariable("id") long id){
		try {
			studyService.deleteStudy(id);
		}catch(Exception e ) {
			e.printStackTrace();
			return  ResponseEntity.status(500).body("스터디 삭제 실패 "+FAIL);
		}
		logger.debug("스터디 삭제 성공");
				
		return ResponseEntity.status(204).body(200 + "Success");
	}
	
	@PutMapping("/update")
	@ApiOperation(value = "스터디 정보 수정", notes = "스터디 정보 수정")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "스터디 없음"),
					@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<String> updateStudy(@RequestBody StudyCreatePostReq studyDto) throws Exception{
		Study study;
		try {
			study = studyService.findById(studyDto.getId());
		}catch(NoSuchElementException E) {
			System.out.println("게시글 수정 실패");
			return  ResponseEntity.status(500).body("해당 게시글이 없어서 게시글 수정 실패");
		}
		Study updateStudy = studyService.updateStudy(study, studyDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@GetMapping("/search/{id}")
	@ApiOperation(value = "스터디 상세 정보 표시", notes = "스터디에 대한 상세 정보 표시")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "스터디 없음"),
					@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<Study> findOneStudy(@PathVariable("id") Long id) {
		Study study = studyService.findById(id);
		return new ResponseEntity<Study>(study, HttpStatus.OK);
	}
	
	
	
	
	/**
	스터디 가입 신청 관련
	*/
	
	@PostMapping("/member/joinstudy/{study_pk}")
	@ApiOperation(value = "스터디 가입신청(token)", notes = "스터디 가입신청 .")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "스터디 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<? extends BaseResponseBody> joinStudy(
			@RequestBody @ApiParam(value="스터디 가입신청", required = true) @PathVariable long study_pk, @ApiIgnore Authentication authentication ){
		
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		long user_pk = userDetails.getUserId();
		int check = studyService.joinStudy(study_pk, user_pk);
		if(check == 1) {//성공
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		}
		else if(check == 0) {	//이미 신청해서 실패
			return ResponseEntity.status(404).body(BaseResponseBody.of(404, "Fail:done"));
		}
		else if(check == 2) { //멤버여서 실패
			return ResponseEntity.status(404).body(BaseResponseBody.of(404, "Fail:member"));
		}
		return ResponseEntity.status(404).body(BaseResponseBody.of(404, "Fail"));
	}
	
	@GetMapping("/member/joinlist/{study_pk}")
	@ApiOperation(value = "스터디가입 신청 멤버 리스트", notes = "스터디가입 신청 멤버 리스트")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "스터디 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<List<StudyApplyListRes>> getJoinList(@PathVariable long study_pk){
		List<StudyApplyListRes> joinlist = studyService.studyJoinList(study_pk);
		
		return new ResponseEntity<List<StudyApplyListRes>>(joinlist, HttpStatus.OK);
		
	}
	
	@PostMapping("/member/joinagree")
	@ApiOperation(value = "스터디 가입신청 승인", notes = "스터디 가입신청 승인.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "스터디 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<? extends BaseResponseBody> agreeStudy(
			@RequestBody @ApiParam(value="스터디 가입신청 승인", required = true) StudyMemberSaveReq studyMemberSaveReq ){
		Study_Member studyMember;
		studyMember = studyService.insertMember(studyMemberSaveReq);
		//  스터디 어플라이에서 삭제
		long id = studyMemberSaveReq.getId();
		System.out.println(">>>>>>>>>>>>>>>"+ id);
		StudyApplyDelete(id);
		System.out.println("가입성공 신청에서 삭제 확인");
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@DeleteMapping("/member/joindelete/{id}")
	@ApiOperation(value = "스터디 가입신청 거절", notes = "스터디 가입신청 거절") 
    @ApiResponses({
        @ApiResponse(code = 204, message = "성공"),
    })
	public ResponseEntity<String> StudyApplyDelete(@PathVariable("id") long id){
		
		try {
			studyService.joindelete(id);
		}catch(Exception e ) {
			e.printStackTrace();
			return  ResponseEntity.status(500).body("삭제 실패 "+FAIL);
		}
		logger.debug("스터디 가입신청 거절 성공");
				
		return ResponseEntity.status(204).body(200 + "Success");
	}
	
	
	@GetMapping("/member/studylistA3")
	@ApiOperation(value = "권한있는 가입된 스터디 목록(token) '참여하기 게시판용 ' ", notes = "권한있는 가입된 스터디 목록 '참여하기 게시판용' ")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "스터디 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<List<StudyMyList>> getStudyListA3(@ApiIgnore Authentication authentication ){
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();  
		long user_id = userDetails.getUserId();
		
		List<StudyMyList> studyListA = studyService.studyListA3(user_id);
		
		return new ResponseEntity<List<StudyMyList>>(studyListA, HttpStatus.OK);
	}
	
	
	@GetMapping("/member/mystudylist")
	@ApiOperation(value = "가입된 스터디 목록(token) ", notes = "가입된 스터디 목록 ")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "스터디 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<List<StudyMyList>> getStudyList(@ApiIgnore Authentication authentication ){
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		long user_id = userDetails.getUserId();
		
		List<StudyMyList> studyListA = studyService.studyList(user_id);
		
		return new ResponseEntity<List<StudyMyList>>(studyListA, HttpStatus.OK);
	}
	
	@GetMapping("/member/studymemberlsit/{study_pk}")
	@ApiOperation(value = "스터디 멤버 리스트", notes = "스터디 멤버 리스트")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "스터디 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<List<StudyMemberList>> studyMemberLsit(@PathVariable long study_pk){
		List<StudyMemberList> joinlist = studyService.studyMemberLsit(study_pk);
		
		return new ResponseEntity<List<StudyMemberList>>(joinlist, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/member/studymemberout/{id}")
	@ApiOperation(value = "스터디 가입신청 거절", notes = "스터디 가입신청 거절") 
    @ApiResponses({
        @ApiResponse(code = 204, message = "성공"),
    })
	public ResponseEntity<String> StudyMemberOut(@PathVariable("id") long id){
		
		try {
			studyService.joindelete(id);
		}catch(Exception e ) {
			e.printStackTrace();
			return  ResponseEntity.status(500).body("삭제 실패 "+FAIL);
		}
		logger.debug("스터디 가입신청 거절 성공");
				
		return ResponseEntity.status(204).body(200 + "Success");
	}
	
	@DeleteMapping("/member/studymemberout")
	@ApiOperation(value = "스터디 탈퇴 및 강퇴(token)", notes = "스터디 탈퇴 및 강퇴") 
	@ApiResponses({
	    @ApiResponse(code = 204, message = "성공"),
	})
	public ResponseEntity<String> StudyMemberOut(
	        @RequestHeader @ApiParam(value="user_pk", required = true) long user_pk,
	        @RequestHeader @ApiParam(value="study_pk", required = true) long study_pk,
	        @ApiIgnore Authentication authentication) {
	    //임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
	    SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
	    long master_id = userDetails.getUserId();
	    int sf = 0;
	    
		try {
			if(master_id == user_pk) studyService.studyMemberOutMe(master_id, user_pk, study_pk);
			else sf = studyService.studyMemberOut(master_id, user_pk, study_pk);
		}catch(Exception e ) {
			e.printStackTrace();
			return  ResponseEntity.status(500).body("삭제 실패 "+FAIL);
		}
		if(sf == 0 ) return ResponseEntity.status(500).body("삭제 실패 "+FAIL);
		return ResponseEntity.status(204).body(200 + "Success");
	}
	
	@GetMapping("/member/publicstudylist")
	@ApiOperation(value = "공개 스터디 목록 ", notes = "공개 스터디 목록 ")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "실패"),
					@ApiResponse(code = 404, message = "스터디 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<List<StudyMyList>> getPublicStudyList(){
		List<StudyMyList> studyListA = studyService.publicstudyList();
		
		return new ResponseEntity<List<StudyMyList>>(studyListA, HttpStatus.OK);
	}
}
