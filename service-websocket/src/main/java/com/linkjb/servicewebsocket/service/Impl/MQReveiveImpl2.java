package com.linkjb.servicewebsocket.service.Impl;

import com.linkjb.servicewebsocket.conf.mq.RabbitMqConfig;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author sharkshen
 * @description mq监听器第二种实现方法
 * @data 2019/5/17 16:23
 */
@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(value = "third-queue"),
                exchange=@Exchange(value= RabbitMqConfig.EXCHANGE,type= ExchangeTypes.DIRECT),
                key=RabbitMqConfig.ROUTINGKEY2
        )

)

public class MQReveiveImpl2 {

    /**
     * 接收消息的方法。采用消息队列监听机制
     * @param msg
     */
     @RabbitHandler
     public void doMessage(String msg){
        System.out.println(msg);
     }
}
