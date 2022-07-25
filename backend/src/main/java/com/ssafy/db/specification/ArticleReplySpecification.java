package com.ssafy.db.specification;

import org.springframework.data.jpa.domain.Specification;

import com.ssafy.db.entity.ArticleReply;

public class ArticleReplySpecification {
	
	public static Specification<ArticleReply> equalArticlePk(Long articlePk){
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("article_pk"), articlePk);
	}
	
	public static Specification<ArticleReply> equalId(Long id){
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
	}
	
}