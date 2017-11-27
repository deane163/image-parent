package com.xiaoshu.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 配置 ImageClient的信息，按照权重进行文件的上传操作
 * @author Administrator
 *
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
