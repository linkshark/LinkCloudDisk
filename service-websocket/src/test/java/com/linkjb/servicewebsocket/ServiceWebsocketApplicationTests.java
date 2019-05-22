package com.linkjb.servicewebsocket;

import com.alibaba.fastjson.JSONObject;
import com.linkjb.servicewebsocket.conf.mq.RabbitMqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceWebsocketApplicationTests {
    Logger log = LoggerFactory.getLogger(ServiceWebsocketApplicationTests.class);
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() {
    }
    @Test
    public void Test02() throws InterruptedException {
        while(true){
            Thread.sleep(5000);
            JSONObject s = new JSONObject();
            s.put("name","shark");
            //a
            String uuid = UUID.randomUUID().toString();
            CorrelationData correlationId = new CorrelationData(uuid);
            System.out.println("发送方发送消息,消息为:"+s.toJSONString());
            rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE,RabbitMqConfig.ROUTINGKEY3,s.toJSONString(),correlationId);
            //rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE,RabbitMqConfig.ROUTINGKEY1,s.toJSONString(),correlationId);

        }
    }

    @Test
    public void TopicTest(){
        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(uuid);

        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPICEXCHANGE,"topic.info","向topic.info发送了消息",correlationId);
        log.info("向topic.info发送了消息");
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPICEXCHANGE,"topic","向topic发送了消息",correlationId);
        log.info("向topic发送了消息");
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPICEXCHANGE,"topic.error","向topic.error发送了消息",correlationId);
        log.info("向topic.error发送了消息");

    }

}
