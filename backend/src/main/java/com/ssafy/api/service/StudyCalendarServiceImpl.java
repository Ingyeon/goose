package com.ssafy.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.studyCalendar.StudyCalendarReq;
import com.ssafy.db.entity.StudyCalendar;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.StudyCalendarRepository;


/**
 *	스터디 캘린더 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("studyCalendarService")
public class StudyCalendarServiceImpl implements StudyCalendarService{
	
	@Autowired
	StudyCalendarRepository studyCalendarRepository;

	@Override
	public StudyCalendar createStudyCalendar(User user, StudyCalendarReq cal) {
		StudyCalendar calendar = new StudyCalendar();
		calendar.setUser_pk(user.getId());
		calendar.setWriter(user.getName());
		
		calendar.setStudy_pk(cal.getStudy_pk());
		calendar.setTitle(cal.getTitle());
		calendar.setContent(cal.getContent());
		calendar.setStart(cal.getStart());
		calendar.setEnd(cal.getEnd());
		calendar.setTextColor(cal.getTextColor());
		calendar.setBackgroundColor(cal.getBackgroundColor());
		calendar.setBorderColor(cal.getBorderColor());
		return studyCalendarRepository.save(calendar);
	}

	@Override
	public StudyCalendar updateStudyCalendar(Long id, User user, StudyCalendarReq cal) {
		StudyCalendar calendar = studyCalendarRepository.getOne(id);
		calendar.setWriter(user.getName());
		
		calendar.setStudy_pk(cal.getStudy_pk());
		calendar.setTitle(cal.getTitle());
		calendar.setContent(cal.getContent());
		calendar.setStart(cal.getStart());
		calendar.setEnd(cal.getEnd());
		calendar.setTextColor(cal.getTextColor());
		calendar.setBackgroundColor(cal.getBackgroundColor());
		calendar.setBorderColor(cal.getBorderColor());
		return studyCalendarRepository.save(calendar);
	}

	@Override
	public StudyCalendar getStudyCalendarById(Long id) {
		StudyCalendar calendar = studyCalendarRepository.getOne(id);
		return calendar;
	}

	@Override
	public void deleteStudyCalendarById(Long id) {
		studyCalendarRepository.deleteById(id);
	}

	@Override
	public List<StudyCalendar> getCalendars(Specification<StudyCalendar> spec) {
		List<StudyCalendar> calendars = studyCalendarRepository.findAll(spec);
		return calendars;
	}
}
