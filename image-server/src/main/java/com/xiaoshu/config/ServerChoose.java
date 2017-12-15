package com.xiaoshu.config;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
 * @Description : 服务器选择,服务器信息动态获取
 * ---------------------------------
 * @Author : deane.administrator
 * @Date : Create in 2017年12月15日下午4:43:17
 * 
 * Copyright (C)2013-2017 小树盛凯科技 All rights reserved.
 */
@Component
public class ServerChoose {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private CopyOnWriteArrayList<ServerConfig> servers;
	private Random random = new Random();
	private int factor;
	private boolean init = false;
	
	//初始胡服务器配置
	public synchronized void init(){
		if(!init){
			synchronized (ServerChoose.class) {
				refresh();
				this.init = true;
			}
		}
	}
	
	//刷新服务器配置
	public synchronized void refresh(){
		//从数据库中读取所有的服务器信息
		//servers = findAll();
		this.factor = 0;
		logger.info("====>Initial the ImageServer, The Pools client sizie is =====> {}", servers.size());
		for(Iterator<ServerConfig> iter = servers.iterator(); iter.hasNext();){
			ServerConfig server = iter.next();
			server.setStartWeight(factor);
			factor += server.getWeight();
		}
	}
	
	/**
	 * 服务器权重分配算法，根据权重信息选择对应的服务器
	 * @return
	 */
	public ServerConfig chooseServer(){
		if(!init){
			//如果没有初始化，先初始化操作
			init();
		}
		ServerConfig selector = null;
		int rv = random.nextInt(factor);
		while(null == selector){
			for(int i= servers.size()-1 ;i>=0; i--){
				ServerConfig server = servers.get(i);
				if(rv > server.getStartWeight()){
					selector = server;
					break;
				}
			}
		}
		return selector;
	}
	
	public CopyOnWriteArrayList<ServerConfig> getServers() {
		return servers;
	}
	public void setServers(CopyOnWriteArrayList<ServerConfig> servers) {
		this.servers = servers;
	}
	
}
