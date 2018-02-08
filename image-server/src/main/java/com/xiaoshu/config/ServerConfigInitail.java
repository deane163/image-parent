package com.xiaoshu.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaoshu.config.zookeeper.DistributedClient;

/***
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
 * @Description : 启动服务端时，监听Zookeeper上注册的服务器信息
 * ---------------------------------
 * @Author : deane.administrator
 * @Date : Create in 2017年12月14日下午7:43:50
 * 
 * Copyright (C)2013-2017 小树盛凯科技 All rights reserved.
 */
@Component
public class ServerConfigInitail {

	@Autowired
	private DistributedClient distributeClient;
	@Autowired
	private ServerChoose choose;

	@PostConstruct
	private void initialServerConfig() {
		// 启动Zookeeper 监听，监听Zookeeper上注册的服务信息
		try {
			distributeClient.getOnlineServers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
