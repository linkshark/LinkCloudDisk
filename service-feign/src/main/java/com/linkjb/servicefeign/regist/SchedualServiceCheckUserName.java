package com.linkjb.servicefeign.regist;

import com.linkjb.servicefeign.base.BaseResult;
import com.linkjb.servicefeign.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author sharkshen
 * @data 2019/1/21 16:19
 * @Useage
 */
@FeignClient(value = "service-regist",fallback = SchedualServiceRegistHystric.class)
public interface SchedualServiceCheckUserName {
    @RequestMapping(value = "/User/checkUserName",method = RequestMethod.POST)
    BaseResult<Boolean> checkUserName(@RequestParam(value = "userName") String userName);
    @RequestMapping(value = "/User/Login/Regist",method = RequestMethod.POST)
    BaseResult<User> Regist(@RequestBody User user);
}
