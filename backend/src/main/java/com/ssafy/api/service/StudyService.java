package com.ssafy.api.service;

import com.ssafy.api.request.study.StudyCreatePostReq;
import com.ssafy.db.entity.Study;

public interface StudyService {
	public Study findById(Long id);
	Study createStudy(StudyCreatePostReq studyCreatePostReq);
	void deleteStudy(long id);
	public Study updateStudy(Study study, StudyCreatePostReq studyUpdateInfo);

	
}
