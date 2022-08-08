package com.ssafy.db.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Article;

/*
 * 모집 게시글 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article>{
	List<Article> findAll(Specification<Article> spec);
	Page<Article> findAll(Specification<Article> spec, Pageable pageable);
	
    @Modifying   
    @Query("update Article a set a.hit = a.hit + 1 where a.id = :id")    
    int updateHit(@Param("id")Long id);


}
