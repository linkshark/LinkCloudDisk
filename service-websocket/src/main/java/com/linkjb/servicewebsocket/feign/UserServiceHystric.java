package com.linkjb.servicewebsocket.feign;

import com.alibaba.druid.util.StringUtils;
import com.linkjb.servicewebsocket.base.BaseResult;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author sharkshen
 * @description userService断路器
 * @data 2019/5/7 10:52
 */
@Component
public class UserServiceHystric implements FallbackFactory<UserFeignService> {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceHystric.class);
    public static final String ERR_MSG = "User接口暂时不可用: ";
    //    @Override
//    public BaseResult<Map> getUserByToken(String token) {
//        BaseResult<Map> result = new BaseResult<>();
//        result.setMessage("对不起,后台出了点问题");
//        result.setStatus("500");
//        return result;
//    }

    @Override
    public UserFeignService create(Throwable throwable) {
        String msg = throwable == null?"":throwable.getMessage();
        if(!StringUtils.isEmpty(msg)){
            LOG.error(msg);
        }
        return new UserFeignService() {
            @Override
            public BaseResult<Map> getUserByToken(String Authorization) {
                BaseResult<Map> objectBaseResult = new BaseResult<>();
                objectBaseResult.setStatus("500");
                objectBaseResult.setMessage(msg);
                return objectBaseResult;

            }
        };
    }
}
