package com.ssafy.api.request.articleReply;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 스터디 게시판 댓글 작성 API ([POST] /api/v1/studyArticlesReply) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("StudyArticleReplyRegisterPostRequest")
public class StudyArticleReplyRegisterPostReq {

	@ApiModelProperty(name="스터디 PK", example="(Long) 스터디 PK")
	long study_pk;
	@ApiModelProperty(name="게시글 PK", example="(Long) 게시글 PK")
	long article_pk;
	@ApiModelProperty(name="댓글 내용", example="(String) 댓글 내용")
	String re_content;
}