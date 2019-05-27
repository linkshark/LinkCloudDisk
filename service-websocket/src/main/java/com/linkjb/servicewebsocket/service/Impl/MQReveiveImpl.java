package com.linkjb.servicewebsocket.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkjb.servicewebsocket.service.MyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;

/**
 * @author sharkshen
 * @description MQSimpleMessage接收类
 * @data 2019/5/14 16:21
 */
@Component
public class MQReveiveImpl {
    @Autowired
    MyHandler handler;
    Logger log = LoggerFactory.getLogger(MQReveiveImpl.class);
    @RabbitListener(queues = {"first-queue","second-queue"},containerFactory = "rabbitListenerContainerFactory")
    public void ReceiveSimpleMessage(String jsonObject){
        JSONObject message = (JSONObject)JSON.parse(jsonObject);
        if(message!=null){
            if(message.get("id")!=null){
                log.info(MyHandler.users.toString());
                WebSocketSession sendTo = MyHandler.users.get(message.get("sendTo").toString());
                WebSocketSession session =  MyHandler.users.get(message.get("id").toString());
                log.info(session.toString());
                if (session!=null&&!session.isOpen()) {
                   log.info("用户未登陆");
                }
                try {
                    //session.sendMessage(new TextMessage("rabbitMQ消息测试成功"+message.get("message")));
                    if(sendTo!=null&&sendTo.isOpen()){
                        sendTo.sendMessage(new TextMessage("rabbitMQ消息测试成功"+message.get("message")));
                    }else{
                        log.info("对方未上线");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                log.info("用户未注册");
            }
        }else{
            log.info("传输了错误信息,信息为空");
        }
       // log.info("接受者收到消息"+jsonObject);
    }
}

