package com.linkjb.servicewebsocket.feign;

import com.linkjb.servicewebsocket.base.BaseResult;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author sharkshen
 * @description userService断路器
 * @data 2019/5/7 10:52
 */
@Component
public class UserServiceHystric implements UserFeignService {
    @Override
    public BaseResult<Map> getUserByToken(String token) {
        BaseResult<Map> result = new BaseResult<>();
        result.setMessage("对不起,后台出了点问题");
        result.setStatus("500");
        return result;
    }
}
