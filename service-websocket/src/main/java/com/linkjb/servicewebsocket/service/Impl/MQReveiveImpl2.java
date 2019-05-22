package com.linkjb.servicewebsocket.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkjb.servicewebsocket.conf.mq.RabbitMqConfig;
import com.linkjb.servicewebsocket.service.MyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

/**
 * @author sharkshen
 * @description mq监听器第二种实现方法
 * @data 2019/5/17 16:23
 */
@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(value = "third-queue"),
                exchange=@Exchange(value= RabbitMqConfig.EXCHANGE,type= ExchangeTypes.DIRECT),
                key=RabbitMqConfig.ROUTINGKEY3
        )
)
public class MQReveiveImpl2 {
    @Autowired
    MyHandler handler;
    Logger log = LoggerFactory.getLogger(MQReveiveImpl2.class);

    /**
     * 接收消息的方法。采用消息队列监听机制
     * @param msg
     */
     @RabbitHandler
     public void doMessage(String msg){

         JSONObject message = (JSONObject) JSON.parse(msg);
         if(message!=null){
             MyHandler.users.forEach((key,session) ->{
                 if (session!=null&&!session.isOpen()) {
                     log.info("用户未登陆");
                 }else{
                     try{
                         handler.sendMessageToUser(message.get("id")+"",new TextMessage(message.get("message")+""));
                         //session.sendMessage(new TextMessage(message.get("message")+""));
                     }catch (Exception e){
                         e.printStackTrace();
                     }
                 }
             });
         }else{
             log.info("传输了错误信息,信息为空");
         }


     }
}
