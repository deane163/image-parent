package com.xiaoshu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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
@Api(value = "Monitor API",description ="monitor Api interface")
public class MainController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ExecutorService executor = Executors.newFixedThreadPool(1);
	public final long WAIT_MAX_TIME_SECOND = 10;
	
	
	@GetMapping(value ="/status",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value ="Server Status")
	public String getServerStatus(){
		logger.info("start monitor the system status...on time:{}",new Date());
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

		}
		//根据数据库接口进行判断
		resultMap.put("code", 200);
		resultMap.put("msg", "success");
		return JSON.toJSONString(resultMap);		
	}
}
