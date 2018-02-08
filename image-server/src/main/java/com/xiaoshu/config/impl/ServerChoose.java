package com.xiaoshu.config.impl;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xiaoshu.config.Choose;
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
@Component(value = "serverChoose")
public class ServerChoose extends Choose{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private CopyOnWriteArrayList<ServerConfig> servers;
	private Random random = new Random();
	private int factor;
	private boolean init = false;
	/**
	 * 锁，使用ReentrantLock 对代码段进行锁定操作
	 */
	private ReentrantLock lock = new ReentrantLock();
	
	//初始话服务器配置
	@Override
	public  void init(){
		lock.lock();
		try {
			if(!init){
				refresh();
				this.init = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	//刷新服务器配置
	@Override
	public void refresh(){
		//从数据库中读取所有的服务器信息
		lock.lock();
		try {
			this.factor = 0;
			logger.info("====>Initial the ImageServer,The Pools client sizie is =====> {}", servers.size());
			for(Iterator<ServerConfig> iter = servers.iterator(); iter.hasNext();){
				ServerConfig server = iter.next();
				server.setStartWeight(factor);
				factor += server.getWeight();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	/**
	 * 服务器权重分配算法，根据权重信息选择对应的服务器
	 * @return
	 */
	@Override
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
	
	@Override
	public void setServers(CopyOnWriteArrayList<ServerConfig> servers) {
		this.servers = servers;
	}
	
}
