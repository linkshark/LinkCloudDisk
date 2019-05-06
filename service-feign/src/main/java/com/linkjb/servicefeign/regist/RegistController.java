package com.linkjb.servicefeign.regist;

import com.linkjb.servicefeign.base.BaseResult;
import com.linkjb.servicefeign.entity.User;
import org.apache.kafka.common.security.authenticator.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sharkshen
 * @data 2019/1/21 16:22
 * @Useage
 */
@RestController
public class RegistController {
    @Resource
    SchedualServiceCheckUserName schedualServiceCheckUserName;

    @PostMapping(value = "/User/Login/checkUserName")
    public BaseResult<Boolean> checkUserName(@RequestParam String userName) {
       try{
           return schedualServiceCheckUserName.checkUserName(userName);
       }catch (Exception e){
           e.printStackTrace();
       }
       return null;
    }
    @PostMapping(value = "/User/Login/Regist")
    public BaseResult<User> Regist(User user) {
        System.out.println(user);
        return schedualServiceCheckUserName.Regist(user);
    }
}
