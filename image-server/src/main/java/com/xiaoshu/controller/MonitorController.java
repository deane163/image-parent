package com.xiaoshu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.xiaoshu.annotation.Authorization;
import com.xiaoshu.config.impl.ServerChoose;
import com.xiaoshu.model.ServerConfig;

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
 * @Description :  Monitor the Server status Info
 * ---------------------------------
 * @Author : deane.administrator
 * @Date : Create in 2017年12月14日下午7:46:42
 * 
 * Copyright (C)2013-2017 小树盛凯科技 All rights reserved.
 */
@RestController
@RequestMapping(value="/system")
@Api(value="System Monitor API",description ="monitor api interface")
@Authorization
public class MonitorController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ServerChoose servers;
	
	private ExecutorService executor = Executors.newFixedThreadPool(1);
	private final long WAIT_MAX_TIME_SECOND = 10;
	
	
	@GetMapping(value ="/heartCheck",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParam(name="authorization" ,value="token",dataType ="String",paramType ="header")
	@ApiOperation(value ="Server Status")
	public String getServerStatus(){
		logger.info("start monitor the system status... on date:{}",new Date());
		Callable<Map<String,Object>> call = new Callable<Map<String,Object>>() {
			Map<String, Object> ret = new HashMap<>();
			@Override
			public Map<String, Object> call() throws Exception {
				// 检查数据库连接是否正常，将状态值设置到ret中，进行返回
				return ret;
			}
		};
		Map<String, Object> resultMap =new HashMap<>();
		Future<Map<String,Object>> future = executor.submit(call);
		try {
			resultMap = future.get(WAIT_MAX_TIME_SECOND, TimeUnit.SECONDS); // 任务处理超时时间设为 10 秒，超时直接返回
		} catch (Exception e) {
			logger.info("任务处理超时，记录异常日志 on time :{}" ,new Date());
		}
		//根据数据库接口进行判断
		resultMap.put("code", 200);
		resultMap.put("msg", "success");
		return JSON.toJSONString(resultMap);		
	}
	
	@GetMapping(value="/servers",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParam(name="authorization" ,value="token",dataType ="String",paramType ="header")
	@ApiOperation(value = "Servers list")
	public String getServerList(){
		logger.info("start 检查已经注册的 Image-client 信息...");
		CopyOnWriteArrayList<ServerConfig> serversList = servers.getServers();
		return JSON.toJSONString(serversList);
	}
}
