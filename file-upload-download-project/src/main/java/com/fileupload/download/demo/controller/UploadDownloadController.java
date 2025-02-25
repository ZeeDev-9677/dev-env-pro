package com.fileupload.download.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.download.demo.services.UploadDownloadService;

@RestController
@RequestMapping("/api")
public class UploadDownloadController {
	@Autowired
	UploadDownloadService uploadDownloadService;

	@RequestMapping(value = "/files", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<>(uploadDownloadService.getAllFiles(), HttpStatus.OK);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<String> uploadAll(@RequestParam(name = "file") MultipartFile file) {
		String status = uploadDownloadService.saveFile(file);
//		System.out.println(file.getOriginalFilename());
//		System.out.println(file.getName());
//		return ResponseEntity.ok("working!!");
//		if(file.isEmpty()) {
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("request contain file");
//		}
		return "CREATED".equals(status) ? new ResponseEntity<>(HttpStatus.CREATED)
				: ("EXIST".equals(status) ? new ResponseEntity<>(HttpStatus.NOT_MODIFIED)
						: new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED));
	}

	@RequestMapping(value = "/download/{file}", method = RequestMethod.GET, produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<?> downloadAll(@PathVariable("file") String fileName) {
		Resource file = uploadDownloadService.downloadFile(fileName);
		if (file == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(file);
		}
	}

}
