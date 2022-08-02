package com.ssafy.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.api.response.study.StudyMemberList;
import com.ssafy.api.response.study.StudyMyList;
import com.ssafy.db.entity.Study_Member;

@Repository
public interface Study_MemberRepository extends JpaRepository<Study_Member, Long>{

	@Query(value="select s.title, s.id from study_member sm, study s \r\n" +
			 "where sm.user_pk = :user_id and sm.study_pk = s.id and sm.authority >= 3",nativeQuery = true)
	List<StudyMyList> studyListA3(@Param("user_id") long user_id);

	@Query(value="select s.title, s.id, s.image from study_member sm, study s \r\n" +
			 "where sm.user_pk = :user_id and sm.study_pk = s.id",nativeQuery = true)
	List<StudyMyList> studyList(@Param("user_id") long user_id);

//	@Query(value="select * from study_member where study_pk = :study_pk",nativeQuery = true)
//	List<String> findAllMember(@Param("study_pk") long study_pk);	
	@Query(value="select sm.id, sm.user_pk, sm.authority, su.name, su.user_id, sm.study_time from study_member sm, study_user su \r\n" +
			"where sm.study_pk = :study_pk and sm.user_pk = su.id" ,nativeQuery = true)
	List<StudyMemberList> findAllMember(@Param("study_pk") long study_pk);

	@Transactional
	@Modifying
	@Query(value="delete from study_member where user_pk = :user_pk and study_pk = :study_pk", nativeQuery = true)
	int studyMemberOut(@Param("user_pk") long user_pk, @Param("study_pk") long study_pk);

	@Query(value="select count(*) from study_member where study_pk = :study_pk and user_pk = :master_id and authority >= 3" ,nativeQuery = true)
	int studyMemberMasterCheck(@Param("master_id") long master_id, @Param("study_pk") long study_pk);
	
}
