package com.linkjb.servicebase.mqTest;

import com.linkjb.servicebase.conf.RabbitMqConfig;
import com.linkjb.servicebase.pojo.Media;
import com.linkjb.servicebase.service.SpiderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 消息发送  生产者1
 */
@Component
public class FirstSender {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    SpiderService spService;

    /**
     * 发送消息
     * @param uuid
     * @param message  消息
     */
    public void send(String uuid,Object message) throws IOException {
        List<String> allList = new ArrayList<>();
        //List<String> allUrl = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:/allUrl.txt")),
                "UTF-8"));
        String line = null;
        while((line=br.readLine())!=null){
            //allList.add(line);
            uuid = UUID.randomUUID().toString();
            CorrelationData correlationId = new CorrelationData(uuid);
            rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY2,
                    line, correlationId);
        }
        br.close();
//        allList.forEach(i-> {
//            CorrelationData correlationId = new CorrelationData(uuid);
//            rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY2,
//                    i, correlationId);
//        });
//        for(int i=0;i<1000000;i++){
//            CorrelationData correlationId = new CorrelationData(uuid);
//            rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY2,
//                    String.valueOf(i), correlationId);
//        }

    }
}
