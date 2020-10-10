package com.bat.springcloud.middleware.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 批量消息监听
 *
 * @author ZhengYu
 * @version 1.0 2020/6/2 13:52
 **/
@Component
public class BroadcastRabbitmqListener {

    @RabbitListeners(
            {
                    @RabbitListener(
                            containerFactory = "cloudRabbitListenerContainerFactory",
                            bindings = @QueueBinding(
                                    value = @Queue(
                                            value = "v1.queue.common.broadcast",
                                            arguments = {
                                                    @Argument(name = "x-message-ttl", value = "10000", type = "java.lang.Integer"),
                                                    @Argument(name = "x-dead-letter-exchange", value = "v1.exchange.dlx"),
                                                    @Argument(name = "x-dead-letter-routing-key", value = "v1.routingKey.dlx")
                                            }
                                    ),
                                    exchange = @Exchange(value = "v1.exchange.common", type = "topic"),
                                    key = "v1.routingKey.common.broadcast"
                            )
                    ),
                    @RabbitListener(
                            containerFactory = "cloudRabbitListenerContainerFactory",
                            bindings = @QueueBinding(
                                    value = @Queue(
                                            value = "v1.routingKey.dlx",
                                            arguments = @Argument(name = "x-message-ttl", value = "18000000", type = "java.lang.Integer")
                                    ),
                                    exchange = @Exchange("v1.exchange.dlx"),
                                    key = "v1.routingKey.dlx"
                            )
                    )
            })
    public void receiverMsg(Message message, Channel channel, String msg) {
        System.out.println(String.format("接收到消息: [%s]", msg));
    }
}
