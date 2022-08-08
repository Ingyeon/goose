package com.ssafy.db.specification;

import org.springframework.data.jpa.domain.Specification;

import com.ssafy.db.entity.StudyArticleReply;

public class StudyArticleReplySpecification {
	
	public static Specification<StudyArticleReply> equalArticlePk(Long articlePk){
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("article_pk"), articlePk);
	}
	
	public static Specification<StudyArticleReply> equalId(Long id){
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
	}
	
}