package com.xiaoshu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
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
@Api(value ="ImageServer for File upload",description ="api for file upload")
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
    	//upload the file
    	ServerConfig selector = choose.chooseServer();
    	// 检查是否有客户端接入
    	if(!ObjectUtils.isEmpty(selector)){
    		logger.info("start upload file ... on time :{} selector IP is :{}",new Date(), selector.getIp());
        	Map<String,Object> paramMap = new HashMap<>();
        	paramMap.put("file", file);
        	String msg = uploadService.uploadSingleImage(file, paramMap, selector.getIp());
        	
        	return wrapperJsonResult(0,msg);
    	}else{
        	
    		return wrapperJsonResult(-1, "No image_client Resigter");
    	}
    	
    }
    
    @PostMapping(value ={"/uploadBatch","/uploadFiles"},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name="authorization" ,value="token",dataType ="String",paramType ="header")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files){
    	logger.info("start batch upload files ... on time:{}", new Date());
    	//upload  files
    	return null;
    }
    
    
    private String wrapperJsonResult(Integer code , String msg ){
    	Map<String,Object> resultMap = new HashMap<>();
    	resultMap.put("code", code);
    	resultMap.put("msg", msg);
    	return JSON.toJSONString(resultMap);
    }
}
