package com.xiaoshu.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
 * @Description : 配置 ImageClient的信息，按照权重进行文件的上传操作
 * ---------------------------------
 * @Author : deane.administrator
 * @Date : Create in 2017年12月15日下午4:41:28
 * 
 * Copyright (C)2013-2017 小树盛凯科技 All rights reserved.
 */
@Component
public class CommonDataCache {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostConstruct
	public void startServerConfigset(){
		logger.info("//=============================================>");
		logger.info("==>start config The Image Client configuration ...<==");
		logger.info("//=============================================>");
	}
}
