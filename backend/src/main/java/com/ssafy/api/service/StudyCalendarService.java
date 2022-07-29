package com.ssafy.api.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.ssafy.api.request.studyCalendar.StudyCalendarReq;
import com.ssafy.db.entity.StudyCalendar;
import com.ssafy.db.entity.User;

/**
 *	스터디 캘린더 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface StudyCalendarService {
	
	StudyCalendar createStudyCalendar(User user, StudyCalendarReq cal);
	StudyCalendar updateStudyCalendar(Long id,User user, StudyCalendarReq cal);
	StudyCalendar getStudyCalendarById(Long id);
	void deleteStudyCalendarById(Long id);
	
	List<StudyCalendar> getCalendars(Specification<StudyCalendar> spec);

}
