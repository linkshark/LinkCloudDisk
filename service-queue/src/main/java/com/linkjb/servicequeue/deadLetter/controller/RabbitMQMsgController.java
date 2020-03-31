package com.linkjb.servicequeue.deadLetter.controller;

import com.linkjb.servicequeue.deadLetter.send.BusinessMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RabbitMQMsgController
 * @Description TODO
 * @Author shark
 * @Data 2020/1/14 15:02
 **/
@RequestMapping("rabbitmq")
@RestController
public class RabbitMQMsgController {

    @Autowired
    private BusinessMessageSender sender;

    @RequestMapping("sendmsg")
    public void sendMsg(String msg){
        sender.sendMsg(msg);
    }
}
