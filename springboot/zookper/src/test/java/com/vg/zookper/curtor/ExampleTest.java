package com.vg.zookper.curtor;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Random;


//spring 2.2之前为 @RunWith(SpringRunner.class) + @SpringBootTest + @Test(org.junit.Test)
//spring 2.2之后为 @SpringBootTest + @Test(org.junit.jupiter.api.Test)
//@RunWith(SpringRunner.class)
//@SpringBootTest //加载spring上下文（容器）
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExampleTest {

    private static CuratorFramework client;

    /**
     * 创建客户端实例 方式一
     */
    @BeforeAll
//    @Test
    public void connection() {
        /**
         * 重试策略 当客户端与zk服务端连接失败或者超时，curator会使用我们指定的 重试策略进行重试
         * RetryPolicy有多个实现，这里使用ExponentialBackoffRetry策略，重试三次，每次间隔1秒钟。
         */
        RetryPolicy retry = new ExponentialBackoffRetry(1000, 3);

        client =
                CuratorFrameworkFactory.newClient("127.0.0.1", retry); //多节点方式只需要通过半角逗号分割的方式进行连接
        client.start();
        System.out.println("curator客户端启动");
    }

    @AfterAll
    public void close(){
        if (client != null){
            client.close();
            System.out.println("curator关闭连接");
        }
    }


    /**
     * 创建客户端实例 方式一
     */
//    @Test
    public void connection2() {
        RetryPolicy retry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client =
                CuratorFrameworkFactory.builder()
                        .connectString("127.0.0.1:2181")
                        .sessionTimeoutMs(60 * 1000)
                        .connectionTimeoutMs(15 * 1000)
                        .retryPolicy(retry)
                        .build();

        client.start();
        System.out.println("curator客户端启动");
    }

    /**
     * 创建节点
     */
    @Test
    public void createNode() throws Exception {
        int a = (new Random()).nextInt(10000);
        System.out.println("节点：/" + a);

        String path = "/" + a;
        Stat flag = client.checkExists().forPath(path);

        if(flag == null){
            client.create().forPath(path);
            System.out.println("创建：" + path);
        }else{
            System.out.println("节点：" + path + "，已存在");
        }
    }
}
