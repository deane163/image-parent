package com.xiaoshu.config;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaoshu.model.ServerConfig;

@Component
public class ServerConfigInitail {

	@Autowired
	private ServerChoose choose;
	
	@PostConstruct
	private void initialServerConfig(){
		//启动Zookeeper 监听，监听Zookeeper上注册的服务信息
		
		ServerConfig server1  = new ServerConfig();
		server1.setIp("http://127.0.0.1:8040/image-client");
		server1.setWeight(100);
		ServerConfig server2  = new ServerConfig();
		server2.setIp("http://10.10.22.167:8040/image-client");
		server2.setWeight(100);
		
		
		CopyOnWriteArrayList<ServerConfig> servers = new CopyOnWriteArrayList<ServerConfig>();
		servers.add(server1);
		servers.add(server2);
		choose.setServers(servers);
		choose.init();
	}
	
	
	
}
