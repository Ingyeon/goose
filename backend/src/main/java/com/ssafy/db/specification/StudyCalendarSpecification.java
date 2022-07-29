package com.ssafy.db.specification;

import org.springframework.data.jpa.domain.Specification;

import com.ssafy.db.entity.StudyCalendar;

public class StudyCalendarSpecification {
	public static Specification<StudyCalendar> equalStudyPk(Long studyPk) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("study_pk"), studyPk);
	}
}