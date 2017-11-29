package com.xiaoshu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoshu.annotation.Authorization;
import com.xiaoshu.config.ServerChoose;
import com.xiaoshu.model.ServerConfig;
import com.xiaoshu.service.ImageUploadService;

/**
 * Single file upload and multiple files upload
 * @author Administrator
 *
 */
@RestController
@Api(value ="ImageServer for File upload")
@Authorization
public class FileServerController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private ImageUploadService uploadService;
    
    @Autowired
    private ServerChoose choose;
    
    
    @PostMapping(value = {"/uploadSingle","/uploadFile"},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name="authorization" ,value="token",dataType ="String",paramType ="header")
    @ApiOperation(value ="for single file upload ..")
    public String uploadFile(@ApiParam("file") @RequestParam("file") MultipartFile file) throws IOException{
    	logger.info("start upload the file ...");
    	//upload the file
    	ServerConfig selector = choose.chooseServer();
    	System.out.println("===============>" + selector.getIp());
    	Map<String,Object> paramMap = new HashMap<>();
    	paramMap.put("file", file);
    	String result = uploadService.uploadSingleImage(file.getBytes(), paramMap, selector.getIp());
    	return result;
    }
    
    @PostMapping(value ={"/uploadBatch","/uploadFiles"},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name="authorization" ,value="token",dataType ="String",paramType ="header")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files){
    	logger.info("start batch upload files ...");
    	//upload the files
    	return null;
    }
}
