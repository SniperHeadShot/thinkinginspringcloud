package com.bat.springcloud.remotecall.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Ribbon
 *
 * @author ZhengYu
 * @version 1.0 2020/5/20 23:49
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class RibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(RibbonApplication.class, args);
    }
}
