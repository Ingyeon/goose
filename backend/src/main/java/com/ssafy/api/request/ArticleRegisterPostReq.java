package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시글 작성 API ([POST] /api/v1/articles) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("ArticleRegisterPostRequest")
public class ArticleRegisterPostReq {
	@ApiModelProperty(name="게시글 Category", example="article_category")
	String category;
}
