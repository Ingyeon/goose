package com.ssafy.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.article.StudyArticleRegistPostReq;
import com.ssafy.api.request.article.StudyArticleUpdatePatchReq;
import com.ssafy.api.response.article.ArticlesInfoRes;
import com.ssafy.api.service.StudyArticleService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Article;
import com.ssafy.db.entity.StudyArticle;
import com.ssafy.db.specification.StudyArticleSpecification;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 스터디 게시판 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "스터디 게시판 API", tags = {"StudyArticle"})
@RestController
@RequestMapping("/api/v1/studyArticles")
public class StudyArticleController {
	
	@Autowired
	StudyArticleService studyArticleService;
	@Autowired
	UserService userService;
	
	@PostMapping()
	@ApiOperation(value = "게시글 작성", notes = "<strong>게시글 정보</strong>를 통해 게시글을 작성한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })

	public ResponseEntity<? extends BaseResponseBody> registerArticles(
			@RequestBody @ApiParam(value="게시글 작성 정보", required = true) StudyArticleRegistPostReq registerInfo) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		studyArticleService.createArticle(registerInfo);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	@GetMapping("/list")
	@ApiOperation(value = "게시글 전체 or 조건 조회", notes = "게시글의 전체 or 상세 정보를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<Page<StudyArticle>> getArticles(
			@RequestParam(required = true, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "(String) 제목") String title) {
		
	

		PageRequest pageRequest = PageRequest.of(page-1, 10);
		
		Specification<StudyArticle> spec = (root, query, criteriaBuilder) -> null;
		
		if (title != null) {
			spec = spec.and(StudyArticleSpecification.equalTitle(title));
		}
		
		List<StudyArticle> articlesss = studyArticleService.getArticles();
		System.out.println(articlesss);
		List<StudyArticle> articless = studyArticleService.getArticles(spec);
		System.out.println(articless);
		Page<StudyArticle> articles = studyArticleService.getArticles(spec, pageRequest);
		System.out.println(articles);

		for (StudyArticle article : articles) {
			article.setName(userService.findName(article.getUser_pk()));
		}

		return ResponseEntity.status(200).body(articles);
	}
	
	@GetMapping("{id}")
	@ApiOperation(value = "게시글 상세 조회", notes = "게시글의 상세 정보를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<ArticlesInfoRes> getArticle(@RequestParam(value = "게시글 id")Long id) {
		
		studyArticleService.updateHit(id);
		StudyArticle articles = studyArticleService.getArticlesById(id);

		ArticlesInfoRes articleInfoRes = ArticlesInfoRes.of(articles);
		articleInfoRes.setName(userService.findName(articles.getUser_pk()));

		return ResponseEntity.status(200).body(articleInfoRes);
	}
	
	@PatchMapping("{id}")
	@ApiOperation(value = "게시글 정보 수정", notes = "<strong>게시글 정보</strong>를 수정 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> updateArticle(
			@RequestBody @ApiParam(value="게시글 수정 정보", required = true) StudyArticleUpdatePatchReq updateInfo, Long id) {
		
		studyArticleService.updateArticle(id, updateInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@DeleteMapping("{id}")
	@ApiOperation(value = "게시글 삭제", notes = "<strong>게시글 번호</strong>를 통해 게시글 삭제 한다.") 
    @ApiResponses({
        @ApiResponse(code = 204, message = "성공"),
    })
	public ResponseEntity<? extends BaseResponseBody> deleteArticle(
			@RequestBody @ApiParam(value="게시글 id", required = true) Long id) {
		
		studyArticleService.deleteArticlesById(id);
		
		return ResponseEntity.status(204).body(BaseResponseBody.of(204, "Success"));
	}
}
