package com.ssafy.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Study_Member;

@Repository
public interface Study_MemberRepository extends JpaRepository<Study_Member, Long>{

}
