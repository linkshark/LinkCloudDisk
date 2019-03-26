package com.linkjb.servicebase.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sharkshen
 * @data 2019/3/26 14:29
 * @Useage
 */
@RestController
@RequestMapping("user")
public class UserController {
    //模拟高并发的用户注册
    @PostMapping("/regist")
    public String Regist(){
        return null;
    }
}
