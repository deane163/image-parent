package com.xiaoshu.config.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 
 * @author Administrator
 *
 */
@Component("distributeServer")
public class DistributedServer {  
	
	@Value("${zookeeper.configuration.center}")
	private String zookeeperCenter;
    //先在zookeeper服务器上创建一个/servers节点  
    private static final String groupNode = "/servers";  
      
    public static void waitUntilConnected(ZooKeeper testZooKeeper, CountDownLatch testLatch) {  
        if(testZooKeeper.getState() == States.CONNECTING) {  
            try {  
                testLatch.await();  
            } catch (InterruptedException err) {  
                System.out.println("Latch exception");  
            }  
        }  
    }  
      
    static class ConnectedWatcher implements Watcher {  
        private CountDownLatch connectedLatch;  
        ConnectedWatcher(CountDownLatch connectedLatch) {  
            this.connectedLatch = connectedLatch;  /* CountDownLatch实例初始化时设为1即可 */  
        }  
        @Override  
        public void process(WatchedEvent event) {  
            if (event.getState() == KeeperState.SyncConnected) {  
                connectedLatch.countDown(); /* ZK连接成功时，计数器由1减为0 */  
            }  
        }  
    }  
      
    /** 
     * 用于向zookeeper集群注册本服务器节点的信息 
     */  
    public void registerZK(String hostname) throws Exception{  
        CountDownLatch sampleLatch = new CountDownLatch(1);  
        Watcher sampleWatcher = new ConnectedWatcher (sampleLatch);  
        //创建一个zk客户端，定义一个监听器逻辑  
        ZooKeeper zkCli = new ZooKeeper(zookeeperCenter, 2000, sampleWatcher);  
          
        /* 只有当zkCli链接成功（状态为 SyncConnected)时，此函数调用才结束 */  
         waitUntilConnected(zkCli, sampleLatch);  
        /*接下来就可以继续zkCli访问了，避免因为zkCli未连接成功时的访问出错 */  
          
        //利用zk往zookeeper中创建一个临时znode   "/servers/server-randomid"  
        //create  /servers 123
        //String path = zkCli.create(groupNode + "/server", hostname.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
         if(null == zkCli.exists(groupNode, false)){
        	 zkCli.create(groupNode , "服务".getBytes(),Ids.OPEN_ACL_UNSAFE , CreateMode.PERSISTENT);   
         }
         //利用zk往zookeeper中创建一个临时znode   "/servers/server-randomid"  
         String path = zkCli.create(groupNode + "/server", hostname.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);  
         System.out.println("服务器"+hostname +"在zookeeper中注册了一个子节点: "+ path);  
    }  
      
    /** 
     * 模拟服务端的业务处理功能 
     */  
    public void handle(String hostname) throws InterruptedException{  
    	System.out.println("进行暂停程序");
        System.out.println("----服务器---" +hostname +"开始处理自己的业务了.......");  
        Thread.sleep(Long.MAX_VALUE);  
    }  
      
    public static void main(String[] args) throws Exception {  
        //调用registerZK方法往zk注册服务器信息  
        DistributedServer server = new DistributedServer();  
        server.registerZK("Server01");  
          
        //处理自己的业务功能  
        server.handle("Server01");  
    }  
}  