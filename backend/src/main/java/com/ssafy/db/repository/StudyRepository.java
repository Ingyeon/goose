package com.ssafy.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.db.entity.Study;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long>{
	
//	@Query(value = "delete\r\n" + 
//			"from study\r\n" + 
//			"where id = :id", nativeQuery = true)
//	void deleteStudyById(@Param("id") String id);

}
