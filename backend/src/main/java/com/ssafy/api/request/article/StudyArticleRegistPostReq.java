package com.ssafy.api.request.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시글 작성 API ([POST] /api/v1/studyArticles) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("StudyArticleRegistRequest")
public class StudyArticleRegistPostReq {
	@ApiModelProperty(name="유저 PK", example="(Long) 유저 PK")
	long user_pk;
	@ApiModelProperty(name="스터디 PK", example="(Long) 스터디 PK")
	long study_pk;

	@ApiModelProperty(name="게시글 제목", example="(String) 게시글 제목")
	String title;
	@ApiModelProperty(name="게시글 내용", example="(String) 게시글 내용")
	String content;
	@ApiModelProperty(name="게시글 내용", example="(String) 이미지 미사용")
	String image;
}
