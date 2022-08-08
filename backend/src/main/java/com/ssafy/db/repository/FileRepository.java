package com.ssafy.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.db.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {
	
}