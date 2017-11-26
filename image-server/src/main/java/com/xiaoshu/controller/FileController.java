package com.xiaoshu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 单个文件上传和批量文件上传接口信息
 * @author Administrator
 *
 */
@RestController
public class FileController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @PostMapping(value = "/uploadSingle")
    public String uploadFile(@RequestParam("file") MultipartFile file){
    	logger.info("start upload the file ...");
    	//上传文件内容
    	return null;
    }
    
    public String uploadFiles(@RequestParam("files") MultipartFile[] files){
    	logger.info("start batch upload files ...");
    	//批量上传文件
    	return null;
    }
}
