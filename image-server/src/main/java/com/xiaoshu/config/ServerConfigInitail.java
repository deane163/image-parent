package com.xiaoshu.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaoshu.config.zookeeper.DistributedClient;

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
