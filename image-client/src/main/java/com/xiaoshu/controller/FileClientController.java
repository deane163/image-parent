package com.xiaoshu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoshu.service.FileService;

@RestController
@Api("File API")
public class FileClientController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier(value = "fileService")
	private FileService fileService;
	
	
	@PostMapping(value="/uploadSingle", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation("single file upload")
	public String postImage(@ApiParam @RequestParam("file") MultipartFile file){
		logger.info("start upload single file ...");
		fileService.createNewdFile(file);
		return null;
	}
	
	@PostMapping(value ="/uploadMultiple", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation("multiple files upload")
	public String postImages(@ApiParam @RequestParam("file") MultipartFile[] files){
		logger.info("start upload multiple files ...");
		if(files != null && files.length > 0){
			for(MultipartFile file : files){
				fileService.createNewdFile(file);
			}
		}
		
		return null;
	}
	
}
