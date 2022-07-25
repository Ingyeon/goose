package com.ssafy.api.request.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시글  수정 API ([PATCH] /api/v1/articles/{id}) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("ArticleUpdatePatchRequest")
public class ArticleUpdatePatchReq {

<<<<<<< HEAD
	@ApiModelProperty(name="스터디 모집상태", example="article_state")
	String state;
	@ApiModelProperty(name="스터디 카테고리", example="article_category")
	String category;
	@ApiModelProperty(name="게시글 제목", example="article_title")
	String title;
	@ApiModelProperty(name="게시글 내용", example="article_content")
	String content;
	@ApiModelProperty(name="스터디 모집 인원", example="article_recruitment")
=======
	@ApiModelProperty(name="스터디 모집상태", example="(String) 스터디 모집상태")
	String state;
	@ApiModelProperty(name="스터디 카테고리", example="(String) 스터디 카테고리  ")
	String category;
	@ApiModelProperty(name="게시글 제목", example="(String) 게시글 제목")
	String title;
	@ApiModelProperty(name="게시글 내용", example="(String) 게시글 내용")
	String content;
	@ApiModelProperty(name="스터디 모집 인원", example="(int) 스터디 모집인원")
>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)
	int recruitment;
}
