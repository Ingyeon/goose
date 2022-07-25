package com.ssafy.api.request.articleReply;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시글 댓글 수정 API ([PATCH] /api/v1/articlesReply/{id}) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("ArticleReplyUpdatePatchRequest")
public class ArticleReplyUpdatePatchReq {
	@ApiModelProperty(name="댓글 내용", example="(String) 댓글 내용")
	String re_content;
}