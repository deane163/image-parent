package com.xiaoshu.config;


import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.xiaoshu.config.zookeeper.DistributedServer;

/**
 * 
 * @author administrator
 *
 */
@Component
public class ZookeeperRegistry {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DistributedServer distributeServer;
	
	@Value("${zookeeper.registry.value}")
	private String zookeeperRegistryValue;
	
	@PostConstruct
	public void startRegistryZookeeper(){
		logger.info("=========>  start resgister MyServer to Zookeeper ...===========>");
		try {
			//将客户端注册到Zookeeper上面
			distributeServer.registerZK(zookeeperRegistryValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
