package com.bat.springcloud.middleware.rabbitmq.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * RabbitmqSendController
 *
 * @author ZhengYu
 * @version 1.0 2020/6/2 17:37
 **/
@RestController
public class RabbitmqSendController {

    private RabbitTemplate rabbitTemplate;

    @GetMapping("/rabbitmq/send")
    public void testSendRabbitmqMsg(@RequestParam("msg") String msg) {
        String messageUuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();

        rabbitTemplate.convertAndSend("v1.exchange.common", "v1.routingKey.common.broadcast", msg, message -> {
            System.out.println(String.format("Send Msg: 唯一标识=[%s], message=[%s]", messageUuid, JSONObject.toJSONString(message)));
            return message;
        }, new CorrelationData(messageUuid));
    }

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
