package com.ssafy.db.specification;

import org.springframework.data.jpa.domain.Specification;

import com.ssafy.db.entity.StudyArticle;

public class StudyArticleSpecification {
	
	public static Specification<StudyArticle> equalTitle(String title){
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" +title+"%" );
	}
	public static Specification<StudyArticle> equalCategory(String category){
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category);
	}
	public static Specification<StudyArticle> equalStudyPk(Long studyPk){
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("study_pk"), studyPk);
	}

}