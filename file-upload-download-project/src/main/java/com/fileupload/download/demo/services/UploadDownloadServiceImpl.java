package com.fileupload.download.demo.services;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UploadDownloadServiceImpl implements UploadDownloadService {

	@Value("${C:\\Users\\ZEESHAN\\Documents\\test}")
	private String basePath;

	@Override
	public List<String> getAllFiles() {
		File f = new File(basePath);
		File[] fileList = f.listFiles();
		return fileList != null ? Arrays.stream(fileList).map(i -> i.getName()).collect(Collectors.toList()) : null;

	}

	@Override
	public ResponseEntity<String> saveFile() {


		return null;
	}
}
