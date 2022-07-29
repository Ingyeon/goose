package com.ssafy.db.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.StudyCalendar;

/*
 * 스터디 캘린더 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface StudyCalendarRepository extends JpaRepository<StudyCalendar, Long>, JpaSpecificationExecutor<StudyCalendar> {
	List<StudyCalendar> findAll(Specification<StudyCalendar> spec);
}