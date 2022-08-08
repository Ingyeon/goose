package com.ssafy.api.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.response.article.FileDto;
import com.ssafy.db.entity.File;
import com.ssafy.db.repository.FileRepository;



@Service("fileService")
public class FileServiceImpl implements FileService{
	
	@Autowired
	FileRepository fileRepository;

	@Override
	@Transactional
	public Long saveFile(FileDto fileDto) {
		return fileRepository.save(fileDto.toEntity()).getId();
	}

	@Override
	@Transactional
	public FileDto getFile(Long id) {
	       File file = fileRepository.findById(id).get();

	        FileDto fileDto = FileDto.builder()
	                .id(id)
	                .origFilename(file.getOrigFilename())
	                .filename(file.getFilename())
	                .filePath(file.getFilePath())
	                .build();
	        return fileDto;
	}

}
