package com.ssafy.api.response.article;

import java.util.Optional;

import com.ssafy.db.entity.Article;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시글 전체 조회 API ([GET] /api/v1/articles/list) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("ArticlesResponse")
public class ArticlesRes {
	
	@ApiModelProperty(name="게시글 id")
	Long id;
	@ApiModelProperty(name="게시글 제목")
	String title;
	@ApiModelProperty(name="작성 날짜")
	String date;
	@ApiModelProperty(name="모집 상태")
	String state;
	@ApiModelProperty(name="모집 인원")
	int recruitment;
	@ApiModelProperty(name="조회수")
	int hit;
	
	@ApiModelProperty(name="작성자 PK")
	Long user_pk;
	@ApiModelProperty(name="작성자")
	String name;
	
	public static ArticlesRes of(Article articles) {
		ArticlesRes res = new ArticlesRes();
		res.setId(articles.getId());
		res.setTitle(articles.getTitle());
		res.setDate(articles.getDate());
		res.setState(articles.getState());
		res.setRecruitment(articles.getRecruitment());
		res.setHit(articles.getHit());
		

		res.setUser_pk(articles.getUser_pk());
		return res;
	}
}