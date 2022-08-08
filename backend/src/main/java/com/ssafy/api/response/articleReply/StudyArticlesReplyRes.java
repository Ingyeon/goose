package com.ssafy.api.response.articleReply;

import com.ssafy.db.entity.StudyArticleReply;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 스터디 게시판 댓글 전체 조회 API ([GET] /api/v1/studyArticles/list) 요청에 대한 응답값 정의.
 * 스터디 게시판 댓글 상세 조회 API ([GET] /api/v1/studyArticles/{id}) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("StudyArticlesReplyResponse")
public class StudyArticlesReplyRes {
	@ApiModelProperty(name="스터디 id")
	Long study_pk;
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
	
	public static StudyArticlesReplyRes of(StudyArticleReply articlesReply) {
		StudyArticlesReplyRes res = new StudyArticlesReplyRes();
		res.setArticle_pk(articlesReply.getArticle_pk());
		res.setStudy_pk(articlesReply.getStudy_pk());
		res.setId(articlesReply.getId());
		res.setName(articlesReply.getName());
		res.setUser_pk(articlesReply.getUser_pk());
		res.setDate(articlesReply.getDate());
		res.setRe_content(articlesReply.getRe_content());
		return res;
	}
}
