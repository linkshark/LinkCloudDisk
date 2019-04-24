package com.linkjb.serviceregist.controller;

import com.linkjb.serviceregist.annotation.AuthToken;
import com.linkjb.serviceregist.base.BaseResult;
import com.linkjb.serviceregist.base.ConstantSrting;
import com.linkjb.serviceregist.entity.UserLinkMedia;
import com.linkjb.serviceregist.service.UserLinkMediaService;
import com.linkjb.serviceregist.service.UserService;
import com.linkjb.serviceregist.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RestController
public class BookController {
    private static Logger log = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserLinkMediaService userLinkMediaService;
    @Autowired
    RedisUtil redisUtil;

    private static String salt = "sharkshen";

    /**
     *功能描述
     * @author shark
     * @date 2019/4/21
     * @param  * @param token
     * @param mediaId
     * @return com.linkjb.serviceregist.base.BaseResult
     */
    @GetMapping("/Book/{mediaId}")
    @AuthToken
    public BaseResult Book(@RequestHeader String Authorization,@PathVariable String mediaId){
        BaseResult re = new BaseResult();
                try{
                    String returnUserName = redisUtil.get(Authorization);
                    if("".equals(returnUserName)||returnUserName==null){
                        re.setMessage("token验证失败,请重新获取");
                        re.setStatus(ConstantSrting.STATUS_FAIL);
                        return re;
                    }else{
                        Map<Object, Object> hashEntries = redisUtil.getHashEntries("POJO_"+returnUserName);
                        Integer id = (Integer)hashEntries.get("id");
                        UserLinkMedia link = new UserLinkMedia();
                        link.setUserId(id);
                        link.setMediaId(Integer.valueOf(mediaId));
                        link.setSubScribeTime(new Date());
                        userLinkMediaService.Insert(link);
                        re.setStatus(ConstantSrting.STATUS_SUCCESS);
                        re.setMessage("book成功");
                        return re;
                    }
                }catch (Exception e){
                    log.error(e.getMessage());
                    re.setMessage(e.getMessage());
                    re.setStatus(ConstantSrting.STATUS_FAIL);
                }
                return re;

    }
    @DeleteMapping("/Book/{mediaId}")
    @AuthToken
    public BaseResult UnBook(@RequestHeader String Authorization,@PathVariable String mediaId){
        BaseResult re = new BaseResult();
        try{
            String returnUserName = redisUtil.get(Authorization);
            if("".equals(returnUserName)||returnUserName==null){
                re.setMessage("token验证失败,请重新获取");
                re.setStatus(ConstantSrting.STATUS_FAIL);
            }else{
                Map<Object, Object> hashEntries = redisUtil.getHashEntries("POJO_"+returnUserName);
                Integer id = (Integer)hashEntries.get("id");
                UserLinkMedia link = new UserLinkMedia();
                link.setUserId(id);
                link.setMediaId(Integer.valueOf(mediaId));
                UserLinkMedia m = userLinkMediaService.selectByOne(link);
                if(m==null){
                    re.setStatus(ConstantSrting.STATUS_FAIL);
                    re.setMessage("unBook失败,用户未订阅此资源");
                }else{
                    userLinkMediaService.delete(link);
                    re.setStatus(ConstantSrting.STATUS_SUCCESS);
                    re.setMessage("unBook成功");
                }
            }
        }catch (Exception e){
            log.error(e.getMessage());
            re.setMessage(e.getMessage());
            re.setStatus(ConstantSrting.STATUS_FAIL);
        }
        return re;

    }
    @GetMapping("/Book/getAllBook")
    @AuthToken
    public BaseResult<List<Map<String,Object>>> getAllBook(@RequestHeader String Authorization){
        BaseResult<List<Map<String,Object>>> result = new BaseResult<>();
        try{
            String returnUserName = redisUtil.get(Authorization);
            if("".equals(returnUserName)||returnUserName==null){
                result.setMessage("token验证失败,请重新获取");
                result.setStatus(ConstantSrting.STATUS_FAIL);
            }else{
                Map<Object, Object> hashEntries = redisUtil.getHashEntries("POJO_"+returnUserName);
                Integer id = (Integer)hashEntries.get("id");
                List<Map<String,Object>> resultList = new ArrayList<>();
                //阻塞调用
                resultList = userLinkMediaService.getAllBook(id).get();
                //超时调用
                //resultList = userLinkMediaService.getAllBook(id).get(1, TimeUnit.SECONDS);
                result.setEntity(resultList);
                result.setStatus(ConstantSrting.STATUS_SUCCESS);
            }
        }catch (Exception e){
                result.setMessage(e.getMessage());
                result.setStatus(ConstantSrting.STATUS_FAIL);
                log.error(e.getMessage());
        }
        return result;
    }


}
