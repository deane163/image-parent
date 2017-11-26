package com.xiaoshu.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;


/**
 * 获得服务器状态信息
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value="/main")
public class MainController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value="/status",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getServerStatus(){
		logger.info("start get Image client status ...");
		Map<String,Object> resultMap =  new HashMap<>();
		resultMap.put("code", 200);
		resultMap.put("status", "success");
		return JSON.toJSONString(resultMap);
	}
}
