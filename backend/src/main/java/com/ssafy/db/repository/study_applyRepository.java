package com.ssafy.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.api.response.study.StudyApplyListRes;
import com.ssafy.db.entity.Study_Apply;

@Repository
public interface study_applyRepository extends JpaRepository<Study_Apply, Long>{

	@Query(value="select count(*) from study_apply \r\n" +
			"where study_pk = :study_pk and user_pk = :user_pk"
			,nativeQuery = true)
	int studyJoinChechk(@Param("study_pk") long study_pk, @Param("user_pk") long user_pk );
	
	@Query(value="select count(*) from study_member \r\n" +
			"where study_pk = :study_pk and user_pk = :user_pk"
			,nativeQuery = true)
	int studyMemberChechk(@Param("study_pk") long study_pk, @Param("user_pk") long user_pk );

	@Query(value="select study_user.id as user_pk, study_user.user_id, study_user.name, study_apply.study_pk, study_apply.apply_date, study_apply.id \r\n" +
	"from study_apply, study_user where study_apply.study_pk = :study_pk and study_apply.user_pk = study_user.id"
			,nativeQuery = true)
	List<StudyApplyListRes> findAllByStudyPk(@Param("study_pk")long study_pk);
	
}
