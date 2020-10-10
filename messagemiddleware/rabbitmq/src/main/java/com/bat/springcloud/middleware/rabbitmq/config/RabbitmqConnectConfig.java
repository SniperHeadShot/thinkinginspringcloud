package com.bat.springcloud.middleware.rabbitmq.config;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbitmq 配置
 *
 * @author ZhengYu
 * @version 1.0 2020/6/2 13:40
 **/
@Configuration
public class RabbitmqConnectConfig {

    @Bean
    public CachingConnectionFactory cloudConnectionFactory(RabbitmqConfigurationProperties rabbitmqConfigurationProperties) {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(new ConnectionFactory());
        cachingConnectionFactory.setHost(rabbitmqConfigurationProperties.getCloud().getHost());
        cachingConnectionFactory.setPort(rabbitmqConfigurationProperties.getCloud().getPort());
        cachingConnectionFactory.setUsername(rabbitmqConfigurationProperties.getCloud().getUsername());
        cachingConnectionFactory.setPassword(rabbitmqConfigurationProperties.getCloud().getPassword());
        cachingConnectionFactory.setVirtualHost(rabbitmqConfigurationProperties.getCloud().getVirtualHost());

        // 开启消息确认和成功return回调
        cachingConnectionFactory.setPublisherConfirms(true);
        cachingConnectionFactory.setPublisherReturns(true);
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitListenerContainerFactory cloudRabbitListenerContainerFactory(CachingConnectionFactory cloudConnectionFactory) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(cloudConnectionFactory);
        simpleRabbitListenerContainerFactory.setConcurrentConsumers(5);
        simpleRabbitListenerContainerFactory.setConsumerTagStrategy(queue -> String.format("队列[%s]消费者", queue));
        return simpleRabbitListenerContainerFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory cloudConnectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cloudConnectionFactory);

        // 此参数的含义是将没有到队列的消息持久化
        rabbitTemplate.setMandatory(true);
        // 消息没有发送到交换机则执行此回调函数
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String msg = String.format("ReturnCallback message=[%s], replyCode=[%d], replyText=[%s], exchange=[%s], routingKey=[%s]", JSONObject.toJSONString(message), replyCode, replyText, exchange, routingKey);
            System.out.println(msg);
        });
        // 消息没有发送到队列则执行此回调函数
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            String msg = String.format("ConfirmCallback correlationData=[%s], ack=[%b], cause=[%s]", JSONObject.toJSONString(correlationData), ack, cause);
            System.out.println(msg);
        });
        return rabbitTemplate;
    }
}
