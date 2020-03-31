package com.linkjb.servicequeue.deadLetter.send;

import com.linkjb.servicequeue.deadLetter.conf.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName BusinessMessageSender
 * @Description TODO
 * @Author shark
 * @Data 2020/1/14 15:01
 **/
@Component
public class BusinessMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg){
        rabbitTemplate.convertSendAndReceive(RabbitMQConfig.BUSINESS_EXCHANGE_NAME, "", msg);
    }
}
