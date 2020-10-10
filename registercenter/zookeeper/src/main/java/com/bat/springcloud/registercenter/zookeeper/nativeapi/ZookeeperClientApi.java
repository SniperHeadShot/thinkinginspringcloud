package com.bat.springcloud.registercenter.zookeeper.nativeapi;

import com.alibaba.fastjson.JSONObject;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Zookeeper 原生 API
 *
 * @author ZhengYu
 * @version 1.0 2020/10/9 13:25
 **/
public class ZookeeperClientApi {

    private static final String ZK_ADDR = "47.100.114.192:2181";

    private static final String DIR_PATH = "/quick_start_dir";
    private static final String NODE_PREFIX = DIR_PATH + "/key_";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");

    public static void main(String[] args) throws Exception {
        nativeApi();
    }

    public static void nativeApi() throws Exception {
        CountDownLatch connectCountDownLatch = new CountDownLatch(1);
        ZooKeeper zk = new ZooKeeper(ZK_ADDR, 60000, event -> {
            // 监听连接建立事件
            System.out.println(String.format("客户端 %s 接收到事件 %s", Thread.currentThread().getName(), JSONObject.toJSONString(event)));
            if (Watcher.Event.KeeperState.SyncConnected == event.getState()) {
                connectCountDownLatch.countDown();
            }
        });
        // 等待建立连接
        connectCountDownLatch.await();

        // 判断路径是否存在
        Stat exists = zk.exists(DIR_PATH, null);
        if (exists == null) {
            // 创建目录
            String dirResult = zk.create(DIR_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(String.format("创建目录节点 %s 返回结果: %s", DIR_PATH, dirResult));
        }
        // 添加监听
        zk.addWatch(DIR_PATH, event -> System.out.println(String.format("目录节点 %s 监听到事件: %s", DIR_PATH, JSONObject.toJSONString(event))), AddWatchMode.PERSISTENT_RECURSIVE);

        // 添加临时顺序节点
        for (int i = 1; i <= 5; i++) {
            zk.create(NODE_PREFIX + LocalDateTime.now().format(DATE_TIME_FORMATTER), String.format("data%d", i).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        }

        // 获取子节点
        List<String> childrenNodes = zk.getChildren(DIR_PATH, false);
        System.out.println(String.format("目录节点 %s 的子节点列表是: %s", DIR_PATH, childrenNodes));

        // 设置值. 版本号 -1 匹配所有版本
        String childPath = DIR_PATH + "/" + childrenNodes.get(0);
        System.out.println("节点 " + childPath + " 将设置新值 ...");
        zk.setData(childPath, "newData1".getBytes(), -1);
        zk.setData(childPath, "newData2".getBytes(), -1);

        Thread.sleep(TimeUnit.SECONDS.toMillis(10));

        // 关闭连接
        zk.close();
        System.out.println(String.format("客户端  %s 连接关闭!", Thread.currentThread().getName()));
    }
}
