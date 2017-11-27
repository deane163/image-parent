package com.xiaoshu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Single file upload and multiple files upload
 * @author Administrator
 *
 */
@RestController
public class FileServerController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @PostMapping(value = "/uploadSingle",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file){
    	logger.info("start upload the file ...");
    	//upload the file
    	return null;
    }
    
    @PostMapping(value ="/uploadBatch",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String uploadFiles(@RequestParam("files") MultipartFile[] files){
    	logger.info("start batch upload files ...");
    	//upload the files
    	return null;
    }
}
