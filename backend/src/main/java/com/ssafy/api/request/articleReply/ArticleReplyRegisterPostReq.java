package com.ssafy.api.request.articleReply;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시글 댓글 작성 API ([POST] /api/v1/articlesReply) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("ArticleReplyRegisterPostRequest")
public class ArticleReplyRegisterPostReq {

	@ApiModelProperty(name="유저 PK", example="user_pk")
	long user_pk;
	@ApiModelProperty(name="게시글 PK", example="article_pk")
	long article_pk;
	@ApiModelProperty(name="댓글 내용", example="articleReply_content")
	String re_content;
}