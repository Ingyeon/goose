package com.ssafy.api.response.study;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
//@ApiModel("StudyMemberList")
public interface StudyMemberList {
	
//	@ApiModelProperty(name="ID")
//	Long id;
//	@ApiModelProperty(name="user_pk")
//	Long user_pk;
//	@ApiModelProperty(name="authority")
//	Long authority;
//	@ApiModelProperty(name="name")
//	String name;
//	@ApiModelProperty(name="user_id")
//	String user_id;
//	@ApiModelProperty(name="study_time")
//	String study_time;
//	
//	
	Long getId();
	Long getUser_pk();
	String getAuthority();
	String getName();
	String getUser_id();
	String getStudy_time();
	String getInfo();
	
}
