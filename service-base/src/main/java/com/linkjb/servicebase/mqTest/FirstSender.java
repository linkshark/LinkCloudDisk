package com.linkjb.servicebase.mqTest;

import com.linkjb.servicebase.conf.RabbitMqConfig;
import com.linkjb.servicebase.pojo.Media;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消息发送  生产者1
 */
@Component
public class FirstSender {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Resource
    private Media media;

    /**
     * 发送消息
     * @param uuid
     * @param message  消息
     */
    public void send(String uuid,Object message) {
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY2,
                message, correlationId);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY2, media,correlationId);
    }
}
