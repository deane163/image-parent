package com.xiaoshu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
 * Monitor the Server status Info
 * @author ubt
 *
 */
@RestController
@RequestMapping(value="/main")
@Api(value="System Monitor API")
public class MainController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value ="/status",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value ="Server Status")
	public String getServerStatus(){
		logger.info("start get the server status ...");
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("code", 200);
		resultMap.put("msg", "success");
		return JSON.toJSONString(resultMap);
	}
}
