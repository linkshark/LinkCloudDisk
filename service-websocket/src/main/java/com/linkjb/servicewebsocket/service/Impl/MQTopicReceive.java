package com.linkjb.servicewebsocket.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author sharkshen
 * @description topic消息监听
 * @data 2019/5/22 14:51
 */
@Component
@RabbitListener(queues = "topic.info")
public class MQTopicReceive {
    Logger log = LoggerFactory.getLogger(MQTopicReceive.class);
    @RabbitHandler
    public void process(String text){
        log.info("topic.info队列监听到消息"+text);
    }
}
