package com.bat.springcloud.middleware.rabbitmq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Rabbitmq Configuration 配置
 *
 * @author ZhengYu
 * @version 1.0 2020/6/2 14:11
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitmqConfigurationProperties {

    /**
     * 云Rabbitmq配置
     */
    private RabbitmqConfigMetadata cloud;
}
