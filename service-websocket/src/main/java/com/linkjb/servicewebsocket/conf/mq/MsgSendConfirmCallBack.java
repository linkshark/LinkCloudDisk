package com.linkjb.servicewebsocket.conf.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * @author sharkshen
 * @description mq确认机制
 * @data 2019/5/14 17:03
 */
public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("MsgSendConfirmCallBack  , 回调id:" + correlationData);
        if (ack) {
            logger.info("消息消费成功");
        } else {
            logger.info("消息消费失败:" + cause + "\n重新发送");
        }
    }
}