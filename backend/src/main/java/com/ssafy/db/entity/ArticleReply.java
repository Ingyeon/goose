package com.ssafy.db.entity;

import javax.persistence.Entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

/**
 * 모집 게시글 댓글 모델 정의.
 */
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class ArticleReply extends BaseEntity{
	long article_pk;
	long user_pk;
	String date;
	String re_content;
	
	String name;
}
