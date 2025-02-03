package com.fileupload.download.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fileupload.download.demo.services.UploadDownloadService;

@RestController
public class UploadDownloadController {
	@Autowired
	UploadDownloadService uploadDownloadService;

	@RequestMapping(value = "/api/h", method = RequestMethod.GET)
	public String func() {
		return "yes yes";
	}

	@RequestMapping(value = "/files/Testt", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<>(uploadDownloadService.getAllFiles(), HttpStatus.OK);
	}

}
