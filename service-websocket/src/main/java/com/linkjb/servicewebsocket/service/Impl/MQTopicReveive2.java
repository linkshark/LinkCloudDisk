package com.linkjb.servicewebsocket.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author sharkshen
 * @description topic监听队列2
 * @data 2019/5/22 14:54
 */
@Component
@RabbitListener(queues = "topic.error")
public class MQTopicReveive2 {
    Logger log = LoggerFactory.getLogger(MQTopicReveive2.class);
    @RabbitHandler
    public void process(String text){
        log.info("topic-error队列监听到消息"+text);
    }
}
