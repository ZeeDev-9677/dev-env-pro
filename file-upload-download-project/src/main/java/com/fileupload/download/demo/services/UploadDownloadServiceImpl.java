package com.fileupload.download.demo.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadDownloadServiceImpl implements UploadDownloadService {

	@Value("${file_destination_path}")
//	i.e file_destination_path=C:\\Users\\rahul\\Documents\\testfile
	private String basePath;

	@Override
	public List<String> getAllFiles() {
		File f = new File(basePath);
		File[] fileList = f.listFiles();
		return fileList != null ? Arrays.stream(fileList).map(i -> i.getName()).collect(Collectors.toList()) : null;

	}

	@Override
	public String saveFile(MultipartFile multipartFile) {

		if (multipartFile.isEmpty()) {
			return "EMPTY";
		}

		File dir = new File(basePath + File.separator + multipartFile.getOriginalFilename());

		if (dir.exists()) {
			return "EXIST";
		}

		Path path = Paths.get(basePath + File.separator + multipartFile.getOriginalFilename());

		try {
			Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			return "CREATED";
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "FAILED";

	}

}
