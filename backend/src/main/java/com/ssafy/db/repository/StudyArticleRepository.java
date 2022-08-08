package com.ssafy.db.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.StudyArticle;

/*
 * 스터디 게시판 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface StudyArticleRepository extends JpaRepository<StudyArticle, Long>, JpaSpecificationExecutor<StudyArticle>{
	List<StudyArticle> findAll(Specification<StudyArticle> spec);
	Page<StudyArticle> findAll(Specification<StudyArticle> spec, Pageable pageable);

	@Modifying   
	@Query("update StudyArticle a set a.hit = a.hit + 1 where a.id = :id")    
	int updateHit(@Param("id")Long id);
}
