package com.xiaoshu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoleilu.hutool.bean.BeanUtil;
import com.xiaoshu.entity.UserToken;
import com.xiaoshu.model.TokenInfo;
import com.xiaoshu.service.UserTokenService;

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
 * @Description : 
 * ---------------------------------
 * @Author : deane.administrator
 * @Date : Create in 2017年12月14日下午7:49:04
 * 
 * Copyright (C)2013-2017 小树盛凯科技 All rights reserved.
 */

@RestController
@RequestMapping(value ="/api")
@Api(value = "API Interface",description ="获取凭证接口服务")
public class ApiController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserTokenService userTokenService;
	
	@GetMapping(value ="/token", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "获得Token凭据")
	public TokenInfo getUserToken(@ApiParam @RequestParam("name") String name,@ApiParam @RequestParam("password") String password){
		logger.info("根据用户名密码，获得Token信息  name:{} and password :{}",name, password);
		TokenInfo token = new TokenInfo();
		if(StringUtils.isEmpty(name) || StringUtils.isEmpty(password)){
			//后面对异常做统一处理，此处先抛出此异常信息
			throw new RuntimeException("参数传递错误 please check name");
		}
		
		List<UserToken> users = userTokenService.selectByNamePassword(name, password);
		if(!CollectionUtils.isEmpty(users)){
			UserToken user = users.remove(0);
			user.setToken(UUID.randomUUID().toString().replace("-", ""));
			user.setExpiredTime(DateUtils.addDays(new Date(), 3).getTime());
			userTokenService.updateById(user);
			//复制信息并返回
			BeanUtil.copyProperties(user, token);
			return token;
		}
		
		return null;
	}
}
