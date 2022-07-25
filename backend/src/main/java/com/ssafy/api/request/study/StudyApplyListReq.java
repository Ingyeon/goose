package com.ssafy.api.request.study;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyApplyListReq {
	int id;
	int user_pk;
	int study_pk;
	String apply_date;
}
