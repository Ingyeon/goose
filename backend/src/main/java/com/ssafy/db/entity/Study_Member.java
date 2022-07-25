package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Study_Member extends BaseEntity{
//	@OnDelete(action = OnDeleteAction.CASCADE)

	@ManyToOne
	@JoinColumn(name = "user_pk")
	private User user_pk;
	
	@ManyToOne
	@JoinColumn(name = "study_pk")
	private Study study_pk;
	  
	
	int authority;
	int attendance;  
	 
	
}
