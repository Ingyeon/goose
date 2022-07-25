package com.ssafy.api.response.article;

import com.ssafy.db.entity.StudyArticle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 스터디 게시판 전체 조회 API ([GET] /api/v1/studyArticles/list) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("StudyArticlesResponse")
public class StudyArticlesRes {
	
	@ApiModelProperty(name="게시글 id")
	Long id;
	@ApiModelProperty(name="게시글 제목")
	String title;
	@ApiModelProperty(name="카테고리")
	String category;
	@ApiModelProperty(name="작성 날짜")
	String date;
	@ApiModelProperty(name="조회수")
	int hit;
	
	@ApiModelProperty(name="작성자 PK")
	Long user_pk;
	@ApiModelProperty(name="작성자")
	String name;
	
	public static StudyArticlesRes of(StudyArticle articles) {
		StudyArticlesRes res = new StudyArticlesRes();
		res.setId(articles.getId());
		res.setTitle(articles.getTitle());
		res.setDate(articles.getDate());
		res.setCategory(articles.getCategory());
		res.setHit(articles.getHit());
		res.setName(articles.getName());

		res.setUser_pk(articles.getUser_pk());
		return res;
	}

}
