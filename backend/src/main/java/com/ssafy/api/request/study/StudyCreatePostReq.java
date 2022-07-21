package com.ssafy.api.request.study;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudyCreatePostRequest")
public class StudyCreatePostReq {

	@ApiModelProperty(name="스터디ID", example="create에선 삭제")
	long id;
	@ApiModelProperty(name="스터디 이름", example="JAVA알고리즘스터디")
	String title;
	@ApiModelProperty(name="인원수", example="1")
	int member;
	@ApiModelProperty(name="최대인원수", example="5")
	int maxmember;
	@ApiModelProperty(name="분류", example="알고리즘")
	String category;
	@ApiModelProperty(name="대표이미지", example="이미지주소값")
	String image;
	@ApiModelProperty(name="공개여부", example="0")
	int open;
	@ApiModelProperty(name="유저 프로필 사진", example="페이지주소")
	String url_page;
	@ApiModelProperty(name="유저 Authority", example="회의주소")
	String url_conf;
	@ApiModelProperty(name="비밀번호", example="1234")
	String password;
	@ApiModelProperty(name="유저 아이디", example="create에선 삭제")
	String userId;
}
