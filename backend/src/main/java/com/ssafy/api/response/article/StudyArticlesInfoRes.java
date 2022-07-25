package com.ssafy.api.response.article;

import com.ssafy.db.entity.StudyArticle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 스터디 게시판 상세 조회 API ([GET] /api/v1/articles/{id}) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("StudyArticlesInfoResponse")
public class StudyArticlesInfoRes {
	@ApiModelProperty(name="게시글 id")
	Long id;
	@ApiModelProperty(name="게시글 제목")
	String title;
	@ApiModelProperty(name="게시글 카테고리")
	String category;
	@ApiModelProperty(name="게시글 내용")
	String content;
	@ApiModelProperty(name="작성 날짜")
	String date;
	@ApiModelProperty(name="조회수")
	int hit;
	
	@ApiModelProperty(name="작성자 PK")
	Long user_pk;
	@ApiModelProperty(name="작성자")
	String name;
	@ApiModelProperty(name="사진")
	String image;
	
	public static StudyArticlesInfoRes of(StudyArticle articles) {
		StudyArticlesInfoRes res = new StudyArticlesInfoRes();
		res.setId(articles.getId());
		res.setCategory(articles.getCategory());
		res.setTitle(articles.getTitle());
		res.setContent(articles.getContent());
		res.setDate(articles.getDate());
		res.setHit(articles.getHit());
		res.setImage(articles.getImage());
		res.setName(articles.getName());
		res.setUser_pk(articles.getUser_pk());
		return res;
	}
}
