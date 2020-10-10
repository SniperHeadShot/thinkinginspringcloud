package com.bat.springcloud.middleware.mosquitto.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * MQTT 配置
 *
 * @author ZhengYu
 * @version 1.0 2020/6/11 10:41
 **/
@Slf4j
public class MQTTConfig {

    private MqttClient mqttClient;

    /**
     * 发送消息
     *
     * @param msg 消息
     * @return boolean 是否成功
     * @author ZhengYu
     */
    public boolean sendMessage(String topic, String msg) {
        if (StringUtils.isEmpty(msg) || mqttClient == null) {
            return false;
        }

        try {
            MqttMessage message = new MqttMessage(msg.getBytes());
            message.setQos(2);
            mqttClient.publish(topic, message);
            return true;
        } catch (MqttException e) {
            log.info("发送 MQTT 消息失败, 原因为: [{}] [{}]", e.getMessage(), e);
        }
        return false;
    }

    /**
     * 建立连接
     *
     * @author ZhengYu
     */
    public void buildMqttClient() {
        try {
            MemoryPersistence persistence = new MemoryPersistence();
            mqttClient = new MqttClient("tcp://47.100.114.192:1883", "mqtt-client", persistence);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setKeepAliveInterval(20);
            options.setConnectionTimeout(10);
            options.setAutomaticReconnect(true);

            // 设置回调
            mqttClient.setCallback(new MqttCallbackExtended() {

                @Override
                public void connectComplete(boolean reconnect, String serverURI) {
                    log.info("建立连接 ... reconnect=[{}], serverURI=[{}]", reconnect, serverURI);
                }

                @Override
                public void connectionLost(Throwable cause) {
                    log.info("连接断开 ...");
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    log.info("接收消息 ... topic=[{}], message=[{}]", topic, JSONObject.toJSONString(message));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    try {
                        if (token != null && token.getMessage() != null) {
                            // 这里不要直接转 token 为 json, 会报错
                            log.info("消息分发完成 ... token=[{}]", JSONObject.toJSONString(token.getMessage()));
                        }
                    } catch (MqttException e) {
                        log.info("消息分发回调异常, 原因: [{}] [{}]", e.getMessage(), e);
                    }
                }
            });

            mqttClient.connect(options);
        } catch (MqttException e) {
            log.info("建立连接过程失败, 原因为: [{}] [{}]", e.getMessage(), e);
        }
    }

    /**
     * 订阅主题
     *
     * @param topic 主题
     * @return boolean
     * @author ZhengYu
     */
    public boolean subscribeTopic(String topic) {
        if (StringUtils.isEmpty(topic) || mqttClient == null) {
            return false;
        }

        try {
            mqttClient.subscribe(topic, 1);
            return true;
        } catch (MqttException e) {
            log.info("订阅通知失败, 原因为: [{}] [{}]", e.getMessage(), e);
        }
        return false;
    }
}
