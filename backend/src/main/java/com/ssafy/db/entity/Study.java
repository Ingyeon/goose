package com.ssafy.db.entity;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.api.request.study.StudyCreatePostReq;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Study extends BaseEntity{

	String title;
	String date;
	int member;
	int maxmember;
	String category;
	String image;
	int open;
	String url_page;
	String url_conf;
	String password;
	
	
	public void updateStudy(StudyCreatePostReq studyUpdateInfo) {
		this.title = studyUpdateInfo.getTitle();
		this.member = studyUpdateInfo.getMember();
		this.maxmember = studyUpdateInfo.getMaxmember();
		this.category = studyUpdateInfo.getCategory();
		this.image = studyUpdateInfo.getImage();
		this.open = studyUpdateInfo.getOpen();
		this.password = studyUpdateInfo.getPassword();
		
	}
  
	
}
