package com.ssafy.api.request.studyCalendar;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 캘린더 작성, 수정 API ([POST] /api/v1/calendar) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("StudyCalendarRequest")
public class StudyCalendarReq {
	
	@ApiModelProperty(name="스터디 PK", example="(Long) 스터디 PK")
	long study_pk;
	
	String title;
	String content;
	String start;
	String end;
	String textColor;
	String backgroundColor;
	String borderColor;
}
