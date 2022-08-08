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

	@ApiModelProperty(name="스터디ID", example="create에서 삭제/update에서 사용")
	long id;
	@ApiModelProperty(name="스터디 이름", example="자바와함께떠나는알고리즘")
	String title;
	@ApiModelProperty(name="현재인원수", example="create에서 1/나머지에선 삭제")
	int member;
	@ApiModelProperty(name="최대인원수", example="5")
	int maxmember;
	@ApiModelProperty(name="분류", example="알고리즘")
	String category;
	@ApiModelProperty(name="대표이미지", example="이미지주소값")
	String image;
	@ApiModelProperty(name="공개여부", example="0-공개 / 1-비공개")
	int open;
	@ApiModelProperty(name="유저 프로필 사진", example="페이지주소")
	String url_page;
	@ApiModelProperty(name="유저 Authority", example="회의주소")
	String url_conf;
	@ApiModelProperty(name="비밀번호", example="1234")
	String password;
	@ApiModelProperty(name="유저 아이디", example="삭제하시오")
	String userId;
}
