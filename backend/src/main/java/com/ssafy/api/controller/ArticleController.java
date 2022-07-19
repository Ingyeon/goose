package com.ssafy.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.ArticleRegisterPostReq;
import com.ssafy.api.service.ArticleService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Article;
import com.ssafy.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 게시글 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "게시글 API", tags = {"Article"})
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {
	
	@Autowired
	ArticleService articleService;

	@PostMapping()
	@ApiOperation(value = "게시글 작성", notes = "<strong>???</strong>를 통해 게시글을 작성한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value="게시글 작성 정보", required = true) ArticleRegisterPostReq registerInfo) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		Article article = articleService.createArticle(registerInfo);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
}
