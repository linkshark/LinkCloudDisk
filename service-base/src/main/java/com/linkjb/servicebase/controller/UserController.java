package com.linkjb.servicebase.controller;

import com.linkjb.servicebase.pojo.User;
import com.linkjb.servicebase.response.CommonReturnType;
import com.linkjb.servicebase.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Queue;
import java.util.UUID;

/**
 * @author sharkshen
 * @data 2019/3/26 14:29
 * @Useage
 */
@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    //模拟高并发的用户订阅
    @GetMapping("/regist/{id}")
    public String getLinkMedia(@PathVariable(value = "id")Integer id){

        //id暂时使用 使用从数据库中查询的id作为入参
        //模拟的随机数
           String orderNo = System.currentTimeMillis() + UUID.randomUUID().toString();
           userService.getLinkMedia(orderNo);
        return "Test ThreadPoolExecutor start!";
    }
    /**
     * 停止服务
     * @param id
     * @return
     */
    @GetMapping("/endregist/{id}")
    public String end(@PathVariable Long id) {

        userService.shutdown();

        Queue q = userService.getMsgQueue();
        log.info("关闭了线程服务，还有未处理的信息条数：" + q.size());
        return "Test ThreadPoolExecutor stop";
    }





}
