package com.bat.springcloud.registercenter.eureka.listen;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Eureka Client 服务状态监听
 *
 * @author ZhengYu
 * @version 1.0 2020/5/20 17:39
 **/
@Slf4j
@Component
public class EurekaClientEventListener {

    /**
     * 监听 服务下线事件
     *
     * @param eurekaInstanceCanceledEvent 服务下线事件
     * @author ZhengYu
     */
    @EventListener
    public void listen(EurekaInstanceCanceledEvent eurekaInstanceCanceledEvent) {
        // 判断是否为master节点
        if (!eurekaInstanceCanceledEvent.isReplication()) {
            log.info("服务下线事件 ==> appName=[{}],serverId=[{}]", eurekaInstanceCanceledEvent.getAppName(), eurekaInstanceCanceledEvent.getServerId());
        }
    }

    /**
     * 监听 服务注册事件
     *
     * @param eurekaInstanceRegisteredEvent 服务下线事件
     * @author ZhengYu
     */
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent eurekaInstanceRegisteredEvent) {
        // 判断是否为master节点
        if (!eurekaInstanceRegisteredEvent.isReplication()) {
            log.info("服务注册事件 ==> instanceInfo=[{}],leaseDuration=[{}]", JSONObject.toJSONString(eurekaInstanceRegisteredEvent.getInstanceInfo()), eurekaInstanceRegisteredEvent.getLeaseDuration());
        }
    }

    /**
     * 监听 服务续约事件
     *
     * @param eurekaInstanceRenewedEvent 服务续约事件
     * @author ZhengYu
     */
    @EventListener
    public void listen(EurekaInstanceRenewedEvent eurekaInstanceRenewedEvent) {
        // 判断是否为master节点
        if (!eurekaInstanceRenewedEvent.isReplication()) {
            log.info("服务续约事件 ==> appName=[{}],serverId=[{}]", eurekaInstanceRenewedEvent.getAppName(), eurekaInstanceRenewedEvent.getServerId());
        }
    }

    /**
     * 监听 Eureka注册中心启动事件
     *
     * @param eurekaRegistryAvailableEvent Eureka注册中心启动事件
     * @author ZhengYu
     */
    @EventListener
    public void listen(EurekaRegistryAvailableEvent eurekaRegistryAvailableEvent) {
        log.info("Eureka注册中心[{}]启动", JSONObject.toJSONString(eurekaRegistryAvailableEvent));
    }

    /**
     * 监听 Eureka Server启动事件
     *
     * @param eurekaServerStartedEvent Eureka Server启动事件
     * @author ZhengYu
     */
    @EventListener
    public void listen(EurekaServerStartedEvent eurekaServerStartedEvent) {
        log.info("Eureka Server[{}]启动", JSONObject.toJSONString(eurekaServerStartedEvent));
    }
}
