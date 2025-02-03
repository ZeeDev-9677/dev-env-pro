package com.fileupload.download.demo.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface UploadDownloadService {

	public List<String> getAllFiles();

	public ResponseEntity<String> saveFile();

}
