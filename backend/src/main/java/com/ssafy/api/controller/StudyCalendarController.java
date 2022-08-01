package com.ssafy.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.article.StudyArticleRegistPostReq;
import com.ssafy.api.request.article.StudyArticleUpdatePatchReq;
import com.ssafy.api.request.studyCalendar.StudyCalendarReq;
import com.ssafy.api.response.article.StudyArticlesInfoRes;
import com.ssafy.api.service.StudyCalendarService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.StudyArticle;
import com.ssafy.db.entity.StudyCalendar;
import com.ssafy.db.entity.User;
import com.ssafy.db.specification.StudyArticleSpecification;
import com.ssafy.db.specification.StudyCalendarSpecification;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 스터디 캘린더 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "캘린더 API", tags = {"Calendar"})
@RestController
@RequestMapping("/api/v1/calendar")
public class StudyCalendarController {
	
	@Autowired
	StudyCalendarService studyCalendarService;
	@Autowired
	UserService userService;
	
	@PostMapping()
	@ApiOperation(value = "캘린더 작성", notes = "<strong>캘린더 정보</strong>를 통해 게시글을 작성한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })

	public ResponseEntity<? extends BaseResponseBody> registerCalendar(
			@RequestBody @ApiParam(value="캘린더 작성 정보", required = true) StudyCalendarReq registerInfo, @ApiIgnore Authentication authentication) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
		studyCalendarService.createStudyCalendar(user, registerInfo);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "캘린더 조회", notes = "캘린더 정보를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<List<StudyCalendar>> getCalendars(
			@RequestParam(required = true) Long studyPk) {
		
		
		Specification<StudyCalendar> spec = (root, query, criteriaBuilder) -> null;
		
		if (studyPk != null) {
			spec = spec.and(StudyCalendarSpecification.equalStudyPk(studyPk));
		}

		
		List<StudyCalendar> calendars = studyCalendarService.getCalendars(spec);

		return ResponseEntity.status(200).body(calendars);
	}
	
	@GetMapping()
	@ApiOperation(value = "캘린더 상세 조회", notes = "캘린더의 상세 정보를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<StudyCalendar> getArticle(@RequestParam(value = "id")Long id) {
		
		StudyCalendar calendar = studyCalendarService.getStudyCalendarById(id);

		return ResponseEntity.status(200).body(calendar);
	}
	
	@PatchMapping()
	@ApiOperation(value = "캘린더 정보 수정", notes = "<strong>캘린더 정보</strong>를 수정 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> updateArticle(
			@RequestBody @ApiParam(value="캘린더 수정 정보", required = true) StudyCalendarReq updateInfo, Long id, @ApiIgnore Authentication authentication) {
		
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
		
		studyCalendarService.updateStudyCalendar(id, user, updateInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@DeleteMapping()
	@ApiOperation(value = "캘린더 삭제", notes = "<strong>캘린더 번호</strong>를 통해 캘린더 삭제 한다.") 
    @ApiResponses({
        @ApiResponse(code = 204, message = "성공"),
    })
	public ResponseEntity<? extends BaseResponseBody> deleteArticle(
			@RequestHeader @ApiParam(value="id", required = true) Long id, @ApiIgnore Authentication authentication) {
		
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		studyCalendarService.deleteStudyCalendarById(id);
		
		return ResponseEntity.status(204).body(BaseResponseBody.of(204, "Success"));
	}

}
