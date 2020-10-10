package com.bat.springcloud.middleware.rabbitmq.config;

import lombok.Data;

/**
 * Rabbitmq Config Metadata
 *
 * @author ZhengYu
 * @version 1.0 2020/6/2 14:35
 **/
@Data
public class RabbitmqConfigMetadata {

    private String host;

    private Integer port;

    private String username;

    private String password;

    private String virtualHost;
}
