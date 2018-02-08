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
import com.xiaoshu.config.Choose;
import com.xiaoshu.model.ServerConfig;
import com.xiaoshu.service.ImageUploadService;

/**
 * 
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 *
 * @Description : Single file upload and multiple files upload
 * ---------------------------------
 * @Author : deane.administrator
 * @Date : Create in 2017年12月15日下午4:44:11
 * 
 * Copyright (C)2013-2017 小树盛凯科技 All rights reserved.
 */
@RestController
@Api(value ="ImageServer for File upload")
@Authorization
public class FileServerController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private ImageUploadService uploadService;
    
    @Autowired
    private Choose choose;
    
    
    @PostMapping(value = {"/uploadSingle","/uploadFile"},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name="authorization" ,value="token",dataType ="String",paramType ="header")
    @ApiOperation(value ="for single file upload ..")
    public String uploadFile(@ApiParam("file") @RequestParam("file") MultipartFile file) throws IOException{
    	logger.info("start upload the file ...");
    	//upload the file
    	ServerConfig selector = choose.chooseServer();
    	Map<String,Object> paramMap = new HashMap<>();
    	paramMap.put("file", file);
    	String result = uploadService.uploadSingleImage(file.getBytes(), paramMap, selector.getIp());
    	return result;
    }
    
    @PostMapping(value ={"/uploadBatch","/uploadFiles"},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name="authorization" ,value="token",dataType ="String",paramType ="header")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files){
    	logger.info("start batch upload files ...");
    	//upload  files
    	return null;
    }
}
