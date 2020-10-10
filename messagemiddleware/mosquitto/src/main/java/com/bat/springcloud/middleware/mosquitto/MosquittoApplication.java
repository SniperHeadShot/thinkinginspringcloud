package com.bat.springcloud.middleware.mosquitto;

import com.bat.springcloud.middleware.mosquitto.config.MQTTConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基于 mosquitto 实现基于 MQTT 协议的消息发布订阅
 *
 * @author ZhengYu
 * @version 1.0 2020/6/10 13:26
 **/
@SpringBootApplication
public class MosquittoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MosquittoApplication.class, args);

        MQTTConfig mqttConfig = new MQTTConfig();
        // 建立MQTT连接
        mqttConfig.buildMqttClient();

        // 订阅主题
        mqttConfig.subscribeTopic("sensor");

        while (true) {
            try {
                Thread.sleep(5000);
                // 发布主题
                mqttConfig.sendMessage("sensor", "Hello Mqtt");
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }
}
