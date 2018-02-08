package com.xiaoshu.config;

import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoshu.model.ServerConfig;

public abstract class Choose {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 初始化服务器配置信息
	 */
	public void init(){
		logger.info("开始初始化服务器配置");
	}
	
	/**
	 *  策略： 可以根据服务器权重、随机、一致性HASH、轮询等负载均衡策略
	 * @return
	 */
	public abstract ServerConfig chooseServer();
	
	/**
	 * 刷新客户端服务器信息
	 */
	public abstract void refresh();
	
	
	/**
	 * 设置服务器信息
	 * @param servers
	 */
	public abstract void setServers(CopyOnWriteArrayList<ServerConfig> servers);
	/**
	 * 服务结束，关闭服务器配置信息
	 */
	public void destory(){
		logger.info("服务结束，开始关闭服务器管理");
	}

}
