package com.ssafy.api.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.study.StudyCreatePostReq;
import com.ssafy.api.service.StudyService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Study;

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
	@ApiOperation(value = "스터디 생성 token인증", notes = "스터디 정보를 입력하고 생성.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
					@ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "스터디 없음"), 
					@ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<? extends BaseResponseBody> createStudy(
			@RequestBody @ApiParam(value="스터디개설 정보", required = true) StudyCreatePostReq studyCreatePosetReq, @ApiIgnore Authentication authentication ){
		
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		long user_id = userDetails.getUserId();
		studyCreatePosetReq.setId(user_id);
		System.out.println(">>>>>>>>>>>>>"+user_id);
		studyService.createStudy(studyCreatePosetReq);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	
	@DeleteMapping("/remove/{id}")
	@ApiOperation(value = "스터디방 삭제", notes = "스터디방을 삭젷나다.") 
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
	
	
	
}
