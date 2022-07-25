package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Study_Apply extends BaseEntity{
	
	
	@ManyToOne
	@JoinColumn(name = "study_pk")
	private Study study_pk;
	
	@ManyToOne
	@JoinColumn(name = "user_pk")
	private User user_pk;
	  
	
	private String apply_date;
	 

}
