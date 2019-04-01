package com.linkjb.servicebase.mqTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FirstConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = {"first-queue","second-queue"}, containerFactory = "rabbitListenerContainerFactory")
    public void handleMessage(String message) throws Exception {
        // 处理消息
        logger.info("FirstConsumer {} handleMessage :"+message);
    }


}
