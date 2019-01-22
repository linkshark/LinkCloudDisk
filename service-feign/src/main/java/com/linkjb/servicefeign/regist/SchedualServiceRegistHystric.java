package com.linkjb.servicefeign.regist;

import com.linkjb.servicefeign.base.BaseResult;
import com.linkjb.servicefeign.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author sharkshen
 * @data 2019/1/22 13:55
 * @Useage
 */
@Component
public class SchedualServiceRegistHystric implements SchedualServiceCheckUserName{
    @Override
    public BaseResult<Boolean> checkUserName(String userName) {
        BaseResult<Boolean> result = new BaseResult<>();
        result.setMessage("对不起 "+ userName+" 后台出了点问题");
        result.setStatus("500");
        return result;
    }

    @Override
    public BaseResult<User> Regist(User user) {
        BaseResult<User> result = new BaseResult<>();
        result.setMessage("对不起 "+ user.getUserName()+" 后台出了点问题");
        result.setStatus("500");
        return result;
    }
}
