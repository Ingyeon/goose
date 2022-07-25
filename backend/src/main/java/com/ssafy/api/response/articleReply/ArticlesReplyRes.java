package com.ssafy.api.response.articleReply;

import com.ssafy.db.entity.ArticleReply;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시글 댓글 전체 조회 API ([GET] /api/v1/articles/list) 요청에 대한 응답값 정의.
 * 게시글 상세 조회 API ([GET] /api/v1/articles/{id}) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("ArticlesReplyResponse")
public class ArticlesReplyRes {
	@ApiModelProperty(name="게시글 id")
	Long article_pk;
	@ApiModelProperty(name="댓글 id")
	Long id;
	@ApiModelProperty(name="작성 날짜")
	String date;
	@ApiModelProperty(name="게시글 제목")
	String re_content;
	
	@ApiModelProperty(name="작성자")
	Long user_pk;
	@ApiModelProperty(name="작성자")
	String name;
	
	public static ArticlesReplyRes of(ArticleReply articlesReply) {
		ArticlesReplyRes res = new ArticlesReplyRes();
		res.setArticle_pk(articlesReply.getArticle_pk());
		res.setId(articlesReply.getId());
		res.setUser_pk(articlesReply.getUser_pk());
		res.setDate(articlesReply.getDate());
		res.setRe_content(articlesReply.getRe_content());
		return res;
	}
}
