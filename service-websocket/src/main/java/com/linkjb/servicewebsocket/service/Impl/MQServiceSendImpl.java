package com.linkjb.servicewebsocket.service.Impl;


import com.alibaba.fastjson.JSONObject;
import com.linkjb.servicewebsocket.conf.mq.RabbitMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author sharkshen
 * @description rabbitmq消息转发类
 * @data 2019/5/13 14:16
 */
@Component
public class MQServiceSendImpl {
    Logger log = LoggerFactory.getLogger(MQServiceSendImpl.class);
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendTo(JSONObject s){
        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(uuid);

        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE,RabbitMqConfig.ROUTINGKEY1,s.toJSONString(),correlationId);
    }
}
