package com.ssafy.api.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.articleReply.ArticleReplyUpdatePatchReq;
import com.ssafy.api.request.articleReply.StudyArticleReplyRegisterPostReq;
import com.ssafy.api.service.StudyArticleReplyService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.StudyArticleReply;
import com.ssafy.db.entity.User;
import com.ssafy.db.specification.StudyArticleReplySpecification;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 스터디 게시판 댓글 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "스터디 게시판 댓글 API", tags = {"StudyArticleReply"})
@RestController
@RequestMapping("/api/v1/studyArticlesReply")
public class StudyArticleReplyController {

	@Autowired
	StudyArticleReplyService studyArticleReplyService;
	@Autowired
	UserService userService;
	
	@PostMapping()
	@ApiOperation(value = "게시글 댓글 작성", notes = "<strong>???</strong>를 통해 게시글 댓글을 작성한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> registerReply(
			@RequestBody @ApiParam(value="게시글 댓글 작성 정보", required = true) StudyArticleReplyRegisterPostReq registerInfo, @ApiIgnore Authentication authentication) {
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
		studyArticleReplyService.createArticleReply(user, registerInfo);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	
	@GetMapping()
	@ApiOperation(value = "게시글 댓글 전체 or 해당 댓글 조회", notes = "게시글 댓글의 전체 정보 or 해당 정보를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<Page<StudyArticleReply>> getArticles(
			@RequestParam(required = true) int page,
			@RequestParam(required = false) Long articlePk,
            @RequestParam(required = false) Long id) {
		
		PageRequest pageRequest = PageRequest.of(page-1, 5);
		
		Specification<StudyArticleReply> spec = (root, query, criteriaBuilder) -> null;
		
		if (articlePk != null) {
			spec = spec.and(StudyArticleReplySpecification.equalArticlePk(articlePk));
		}
		if (id != null) {
			spec = spec.and(StudyArticleReplySpecification.equalId(id));
		}

		Page<StudyArticleReply> articleReplys = studyArticleReplyService.getArticleReply(spec, pageRequest);
	
		return ResponseEntity.status(200).body(articleReplys);
	}
	
	@PatchMapping()
	@ApiOperation(value = "댓글 정보 수정", notes = "<strong>댓글 정보</strong>를 수정 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> updateArticle(
			@RequestBody @ApiParam(value="댓글 수정 정보", required = true) ArticleReplyUpdatePatchReq updateInfo, Long id, @ApiIgnore Authentication authentication) {
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
		studyArticleReplyService.updateArticleReply(id, user, updateInfo);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@DeleteMapping()
	@ApiOperation(value = "게시글 댓글 삭제", notes = "<strong>댓글 번호</strong>를 통해 댓글 삭제 한다.") 
    @ApiResponses({
        @ApiResponse(code = 204, message = "성공"),
    })
	public ResponseEntity<? extends BaseResponseBody> deleteArticleReply(
			@RequestBody @ApiParam(value="id", required = true) Long id, @ApiIgnore Authentication authentication) {
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		studyArticleReplyService.deleteArticleReplyById(id);
		
		return ResponseEntity.status(204).body(BaseResponseBody.of(204, "Success"));
	}
}
