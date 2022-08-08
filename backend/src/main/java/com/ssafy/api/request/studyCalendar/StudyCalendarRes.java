package com.ssafy.api.request.studyCalendar;

import com.ssafy.db.entity.StudyCalendar;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudyCalendarResponse")
public class StudyCalendarRes {
	
	Long groupId;
	Long user_pk;
	Long id;
	String title;
	String content;
	String writer;
	String start;
	String end;
	String textColor;
	String backgroundColor;
	String borderColor;
	
	public static StudyCalendarRes of(StudyCalendar sc) {
		StudyCalendarRes scr = new StudyCalendarRes();
		
		scr.setBackgroundColor(sc.getBackgroundColor());
		scr.setBorderColor(sc.getBorderColor());
		scr.setContent(sc.getContent());
		scr.setEnd(sc.getEnd());
		scr.setGroupId(sc.getStudy_pk());
		scr.setId(sc.getId());
		scr.setStart(sc.getStart());
		scr.setTextColor(sc.getTextColor());
		scr.setTitle(sc.getTitle());
		scr.setUser_pk(sc.getUser_pk());
		scr.setWriter(sc.getWriter());
		
		
		
		return scr;
	}

}
