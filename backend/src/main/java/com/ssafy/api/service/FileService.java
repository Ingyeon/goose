package com.ssafy.api.service;

import com.ssafy.api.response.article.FileDto;

public interface FileService {
	public Long saveFile(FileDto fileDto);
	public FileDto getFile(Long id);
}
