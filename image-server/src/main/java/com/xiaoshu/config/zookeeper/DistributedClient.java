package com.xiaoshu.config.zookeeper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.xiaoshu.config.ServerChoose;
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
 * @Description : Zookeeper客户注册管理
 * ---------------------------------
 * @Author : deane.administrator
 * @Date : Create in 2017年12月14日下午6:48:32
 * 
 * Copyright (C)2013-2017 小树盛凯科技 All rights reserved.
 */
@Component
public class DistributedClient {  
    static ZooKeeper zkCli = null;  
    volatile static List<String> servers = null;  
    
    //先在zookeeper服务器上创建一个/servers节点  ，防止第一次没有初始化时，启动报错
    private static final String groupNode = "/servers"; 
    
	@Value("${zookeeper.configuration.center}")
	private String zookeeperCenter;
    
	@Autowired
	private  ServerChoose choose;
      
    public static void waitUntilConnected(ZooKeeper testZooKeeper, CountDownLatch testLatch) {  
        if(testZooKeeper.getState() == States.CONNECTING) {  
            try {  
                testLatch.await();  
            } catch (InterruptedException err) {  
                System.out.println("Latch exception");  
            }  
        }  
    }  
      
   public  class ConnectedWatcher implements Watcher {  
        private CountDownLatch connectedLatch;  
        public ConnectedWatcher(CountDownLatch connectedLatch) {  
            this.connectedLatch = connectedLatch;  /* CountDownLatch实例初始化时设为1即可 */  
        }  
        @Override  
        public void process(WatchedEvent event) {  
            if (event.getState() == KeeperState.SyncConnected) {  
                connectedLatch.countDown(); /* ZK连接成功时，计数器由1减为0 */  
            }  
            // 从zookeeper中获取最新的服务节点信息  
            try {  
                updateServers();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
      
    /** 
     * 获取在线的服务节点 
     */  
    public void getOnlineServers() throws Exception {  
        CountDownLatch sampleLatch = new CountDownLatch(1);  
        Watcher sampleWatcher = new ConnectedWatcher (sampleLatch);  
        // 构造一个zookeeper的客户端  
        zkCli = new ZooKeeper(zookeeperCenter, 2000, sampleWatcher);  
          
        /* 只有当zkCli链接成功（状态为 SyncConnected)时，此函数调用才结束 */  
         waitUntilConnected(zkCli, sampleLatch);  
        /*接下来就可以继续zkCli访问了，避免因为zkCli未连接成功时的访问出错 */  
          
        // 一进来就调用updateServers方法获取当前在线的服务节点  
        updateServers();  
    }  
  
    /** 
     * 从zookeeper中获取服务节点信息的具体实现方法 
     */  
    public  void updateServers() throws Exception {  
        // 构造一个list用来保存服务节点信息  
        List<String> serverList = new ArrayList<String>();  
  
        //create  /servers 123 
         if(null == zkCli.exists(groupNode, false)){
        	 zkCli.create(groupNode , "服务".getBytes(),Ids.OPEN_ACL_UNSAFE , CreateMode.PERSISTENT);   
         }
        // 先拿到servers下的子节点名称列表,并对父节点servers注册监听器  
        List<String> children = zkCli.getChildren("/servers", true);  
        //遍历获取每一个子节点所保存的数据——服务节点信息  
        for (String child : children) {  
            byte[] data = zkCli.getData("/servers/" + child, false, null);  
            String serverName = new String(data, "utf-8");  
            serverList.add(serverName);  
            System.out.println("当前在线的服务节点有： " + serverName);  
        }  
        servers = serverList;  
        
        CopyOnWriteArrayList<ServerConfig> serverConfigList = new CopyOnWriteArrayList<ServerConfig>();
		//初始化服务器
		for(String server : serverList){
			ServerConfig serverConfig = new ServerConfig();
			serverConfig.setIp(server);
			serverConfig.setWeight(100);
			serverConfigList.add(serverConfig);
		}
		choose.setServers(serverConfigList);
		choose.refresh();
        System.out.println("---------服务节点信息更新完毕---------");  
    }  
  
    /** 
     * 模拟客户端程序的业务功能 
     */  
    public void handle() throws Exception {  
        System.out.println("客户端开始处理自己的业务功能.......");  
        Thread.sleep(Long.MAX_VALUE);  
    }  
  
    public static void main(String[] args) throws Exception {  
        // 获取服务器列表  
        DistributedClient distributedClient = new DistributedClient();  
        distributedClient.getOnlineServers();  
  
        // 处理自己的业务功能  
        distributedClient.handle();  
    }  
}  

