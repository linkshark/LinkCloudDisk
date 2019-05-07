package com.linkjb.servicewebsocket.feign;

import com.linkjb.servicewebsocket.base.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author sharkshen
 * @description feign层调用service-regist服务
 * @data 2019/5/7 10:16
 */
//,fallback = UserServiceHystric.class
@FeignClient(value = "service-regist")
@Component
public interface UserFeignService {
    @RequestMapping(value = "/User/checkTokenByParam",method = RequestMethod.POST)
    BaseResult<Map> getUserByToken(@RequestParam(value = "Authorization")String Authorization);

}
