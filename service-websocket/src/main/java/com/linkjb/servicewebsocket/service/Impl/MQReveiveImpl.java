package com.linkjb.servicewebsocket.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author sharkshen
 * @description MQSimpleMessage接收类
 * @data 2019/5/14 16:21
 */
@Component
public class MQReveiveImpl {
    Logger log = LoggerFactory.getLogger(MQReveiveImpl.class);
    @RabbitListener(queues = {"first-queue","second-queue"},containerFactory = "rabbitListenerContainerFactory")
    public void ReceiveSimpleMessage(String jsonObject){

        log.info("接受者收到消息"+jsonObject);
    }
}

