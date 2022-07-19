package com.ssafy.db.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 게시글 모델 정의.
 */
@Entity
@Getter
@Setter
public class Article extends BaseEntity {
	String category;
}