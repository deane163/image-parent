package com.xiaoshu.config;


import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaoshu.config.zookeeper.DistributedServer;

@Component
public class ZookeeperResigtery {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DistributedServer distributeServer;
	
	
	@PostConstruct
	public void startRegistryZookeeper(){
		logger.info("=========>  start resgister MyServer to Zookeeper ...===========>");
		try {
			//将客户端注册到Zookeeper上面
			distributeServer.registerZK("http://127.0.0.1:8040/image-client");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
