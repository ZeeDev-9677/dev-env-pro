package com.fileupload.download.demo.services;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadDownloadService {

	public List<String> getAllFiles();

	public String saveFile(MultipartFile file);

	public Resource downloadFile(String fileName);

}
