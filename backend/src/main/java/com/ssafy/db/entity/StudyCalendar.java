package com.ssafy.db.entity;

import javax.persistence.Entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

/**
 * 스터디 캘린더 모델 정의.
 */
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class StudyCalendar extends BaseEntity {
	
	Long study_pk;
	Long user_pk;
	String title;
	String content;
	String writer;
	String start;
	String end;
	String textColor;
	String backgroundColor;
	String borderColor;

}
