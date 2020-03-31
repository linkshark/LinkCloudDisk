package com.linkjb.servicequeue.deadLetter.receive;

import com.linkjb.servicequeue.deadLetter.conf.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName DeadLetterMessageReceiver
 * @Description TODO
 * @Author shark
 * @Data 2020/1/14 15:00
 **/
@Component
public class DeadLetterMessageReceiver {


    @RabbitListener(queues = RabbitMQConfig.DEAD_LETTER_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws IOException {
        System.out.println("收到死信消息A：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = RabbitMQConfig.DEAD_LETTER_QUEUEB_NAME)
    public void receiveB(Message message, Channel channel) throws IOException {
        System.out.println("收到死信消息B：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}