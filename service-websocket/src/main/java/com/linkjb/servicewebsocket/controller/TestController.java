package com.linkjb.servicewebsocket.controller;

import com.linkjb.servicewebsocket.base.BaseResult;
import com.linkjb.servicewebsocket.feign.UserFeignService;
import com.linkjb.servicewebsocket.service.MyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @author sharkshen
 * @description websocketTestDemo
 * @data 2019/5/8 11:17
 */
@RestController
public class TestController {
    Logger log = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private AmqpTemplate mqTemplate;
    @Autowired
    private MyHandler websocketTemplet;
    @Resource
    private UserFeignService userFeignService;
    @GetMapping("/User/SendTo/{ID}")
    public void sendMsg(@PathVariable(value = "ID") String ID){
        try{
            BaseResult<Map> userByToken = userFeignService.getUserByToken(ID);
            if(userByToken!=null&&userByToken.getStatus()!="500"){
                boolean b = websocketTemplet.sendMessageToUser(userByToken.getEntity().get("id").toString(), new TextMessage("我也不知道为什么不能IOC注入"));
                mqTemplate.convertAndSend("simpleQueue");
            }
            log.info(userByToken.toString());
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
