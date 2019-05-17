package com.linkjb.servicewebsocket;

import com.alibaba.fastjson.JSONObject;
import com.linkjb.servicewebsocket.conf.mq.RabbitMqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceWebsocketApplicationTests {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() {
    }
    @Test
    public void Test02(){
        JSONObject s = new JSONObject();
        s.put("name","shark");
        //a
        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(uuid);

        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE,RabbitMqConfig.ROUTINGKEY2,s.toJSONString(),correlationId);
    }

}
