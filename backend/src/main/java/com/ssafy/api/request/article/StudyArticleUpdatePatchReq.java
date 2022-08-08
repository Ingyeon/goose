package com.ssafy.api.request.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시글  수정 API ([PATCH] /api/v1/studyArticles/{id}) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("StudyArticleUpdateRequest")
public class StudyArticleUpdatePatchReq {

	@ApiModelProperty(name="게시글 제목", example="(String) 게시글 제목")
	String title;
	@ApiModelProperty(name="게시글 카테고리", example="(String) 게시글 카테고리")
	String category;
	@ApiModelProperty(name="게시글 내용", example="(String) 게시글 내용")
	String content;
	@ApiModelProperty(name="게시글 내용", example="(String) 이미지 미사용")
	String image;
}
