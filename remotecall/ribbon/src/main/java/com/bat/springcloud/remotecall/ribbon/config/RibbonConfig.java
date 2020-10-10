package com.bat.springcloud.remotecall.ribbon.config;

import com.netflix.client.config.IClientConfig;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon Config
 *
 * @author ZhengYu
 * @version 1.0 2020/5/20 23:55
 **/
@Configuration
public class RibbonConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 指定全局的路由规则
     * 指定全局路由规则后无法指定单个服务的路由规则{@link org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration#ribbonRule(IClientConfig)}
     *
     * @author ZhengYu
     */
//    @Bean
//    public IRule ribbonRule() {
//        return new RoundRobinRule();
//    }
}
