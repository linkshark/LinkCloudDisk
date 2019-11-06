package com.linkjb.serviceregist.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkjb.servicepojo.pojo.user.User;
import com.linkjb.serviceregist.annotation.AuthToken;
import com.linkjb.serviceregist.base.BaseResult;
import com.linkjb.serviceregist.base.ConstantSrting;
import com.linkjb.serviceregist.service.UserService;
import com.linkjb.serviceregist.utils.EmailUtils;
import com.linkjb.serviceregist.utils.MD5;
import com.linkjb.serviceregist.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.*;

/**
 * @author sharkshen
 * @data 2019/1/16 16:22
 * @Useage balabala
 */
//@CrossOrigin(origins = "localhost:8078", maxAge = 3600)
@RestController
@RequestMapping
public class UserController {
    //日志记录
    private static Logger Log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    EmailUtils emailUtils;

    private static String salt = "sharkshen";
    /*
     * @Author sharkshen
     * @Description  通过用户名获取用户
     * @Date 2019/1/17
     * @Param [userName]
     * @return com.linkjb.base.BaseResult<java.lang.Boolean>
     **/
    @PostMapping("/User/checkUserName")
    public BaseResult<Boolean> checkUserNameUinque(@RequestBody Map<String,Object> s){
        BaseResult<Boolean> result = new BaseResult<>();
       try{

           Log.info(s.toString());
           //先从redis中获取,不直接查询mysql
           //使用redis 进行查
           if(s.get("userName")!=null&&s.get("userName")!=""){
               String d = redisUtil.get(s.get("userName").toString());
               if(d==null||"".equals(d)){
               result.setEntity(true);
               result.setMessage("用户名可用");
               result.setStatus(ConstantSrting.STATUS_SUCCESS);
               return result;
           }else{
               result.setEntity(false);
               result.setMessage("用户名已被占用");
               result.setStatus(ConstantSrting.STATUS_SUCCESS);
           }
           }else{
               result.setEntity(false);
               result.setStatus(ConstantSrting.STATUS_SUCCESS);
               result.setMessage("userName未传");
           }
       }catch (Exception e){
           result.setStatus(ConstantSrting.STATUS_FAIL);
           e.printStackTrace();
       }
        return result;
    }
    /**
     *功能描述 获取并发送邮箱验证码
     * @author shark
     * @date 2019/4/29
     * @param  * @param emailAddress
     * @return com.linkjb.serviceregist.base.BaseResult
     */
    @PostMapping("/User/getVerificationCode")
    public BaseResult getVerificationCode(@RequestBody Map<String,Object> map){
            BaseResult result = new BaseResult();
            try{
                if(map.get("userName")==null||"".equals(map.get("userName").toString())){
                    result.setMessage("userName:用户名为空");
                    result.setStatus(ConstantSrting.STATUS_FAIL);
                    return  result;
                }
                if(map.get("emailAddress")==null||"".equals(map.get("emailAddress").toString())){
                    result.setMessage("emailAddress:邮箱地址为空");
                    result.setStatus(ConstantSrting.STATUS_FAIL);
                    return result;
                }else if(userService.getUserByEmailAddress(map.get("emailAddress").toString())!=null){
                    result.setMessage("邮箱地址已被注册");
                    result.setStatus(ConstantSrting.STATUS_FAIL);
                    return result;
                }

                String userName = map.get("userName").toString();
                String emailAddress = map.get("emailAddress").toString();
                String verificationCode = String.valueOf((int)((Math.random()*9+1)*100000));
                redisUtil.setForTimeMIN(userName+"-verificationCode",verificationCode,30);//双向绑定
                redisUtil.setForTimeMIN(verificationCode,userName+"-verificationCode",30);//双向绑定
                Log.info("从redis中获取的验证码为"+redisUtil.get(userName+"-verificationCode"));
                emailUtils.sendSimpleEmail("验证码","您的验证码为"+verificationCode+"有效期为30分钟",emailAddress);
                result.setMessage("调用成功");
                result.setEntity(verificationCode);
                result.setStatus(ConstantSrting.STATUS_SUCCESS);
            }catch (Exception e){
                Log.error(e.getMessage());
                result.setMessage(e.getMessage());
                result.setStatus(ConstantSrting.STATUS_FAIL);
            }
            return result;
    };
        /*
         * @Author sharkshen
         * @Description  注册用户
         * @Date  2019/1/17
         * @Param [user]
         * @return com.linkjb.base.BaseResult<com.linkjb.entity.User>
         **/
    @RequestMapping(value="/User/Regist",method = RequestMethod.POST)
    public BaseResult<User> Regist(@RequestBody User user, @RequestHeader String verificationCode){

        BaseResult<User> result = new BaseResult<>();
       try{
           User userByUserName = userService.getUserByUserName(user.getUserName());
           User userByEmailAddress = userService.getUserByEmailAddress(user.getEmail());
           if(userByUserName!=null){
               result.setStatus(ConstantSrting.STATUS_FAIL);
               result.setMessage("已存在相同用户名的用户,请重新选择");
           }else if(userByEmailAddress!=null){
               result.setStatus(ConstantSrting.STATUS_FAIL);
               result.setMessage("此邮箱已被注册,请重新选择");
           }else{
               {
                   String s = redisUtil.get(user.getUserName()+"-verificationCode");
                   if(s==null||!s.equals(verificationCode)){
                       result.setStatus(ConstantSrting.STATUS_FAIL);
                       result.setMessage("验证码过期或不可用");
                   }else{
                       String userPass = user.getPassWord();
                       user.setPassWord(MD5.encryptPassword(userPass,salt));
                       user.setCreateTime(new Date());
                       Integer a = userService.RegistUser(user); //a的值为sql影响的行数,一开始理解错误,是直接将id返回到对象中,所以可以直接返回对象
                       if(a.equals(1)){
                           redisUtil.set(user.getUserName(),user.getPassWord());
                           //实体类转map
                           JSONObject jsonObject  = (JSONObject)JSON.toJSON(user);
                           Set<Map.Entry<String,Object>> entrySet = jsonObject.entrySet();
                           Map<String,Object> map = new HashMap<>();
                           for (Map.Entry<String,Object> entry:
                                   entrySet) {
                               map.put(entry.getKey(), entry.getValue());
                           }
                           redisUtil.putAll("POJO_"+user.getUserName(),map);
                           Map<String, User> hashEntries= (Map)redisUtil.getHashEntries("POJO_"+user.getUserName());
                           //Log.info(hashEntries.toString());
                           result.setEntity(user);
                           result.setStatus(ConstantSrting.STATUS_SUCCESS);
                           return result;
                       }else{
                           result.setStatus(ConstantSrting.STATUS_FAIL);
                           result.setMessage("注册失败");
                       }
                   }
               }
           }

       }catch (Exception e){
           e.printStackTrace();
           result.setMessage(e.getMessage());
           result.setStatus(ConstantSrting.STATUS_FAIL);
       }
        return result;

    }
    /*
     * @Author sharkshen
     * @Description  用户登录验证
     * @Date  2019/1/17
     * @Param [userName,passWord]
     * @return com.linkjb.base.BaseResult<com.linkjb.entity.User>
     **/
    @PostMapping("/User/Login")
    public BaseResult<String> Login(@RequestBody Map<String,Object> map){
        BaseResult<String> result = new BaseResult<>();
        try{
                if(map.get("userName")==null||"".equals(map.get("userName"))){
                    result.setStatus(ConstantSrting.STATUS_FAIL);
                    result.setMessage("userName:用户名未填写");
                }else if(map.get("passWord")==null||"".equals(map.get("passWord"))){
                    result.setStatus(ConstantSrting.STATUS_FAIL);
                    result.setMessage("passWord:密码未填写");
                }else{
                    String userName = map.get("userName").toString();
                    String passWord = map.get("passWord").toString();
                    User user = userService.getUserByUserName(userName);
                    if(user!=null){
                        String checkPass = user.getPassWord();
                        String a = MD5.encryptPassword(passWord,salt);
                        if(MD5.encryptPassword(passWord,salt).equals(checkPass)){
                            //username和token 双向绑定,登录后token有效时间为60分钟
                            redisUtil.setForTimeMS(userName,user.getToken(user),1000*60*60);
                            redisUtil.setForTimeMS(user.getToken(user),userName,1000*60*60);
                            redisUtil.set(userName+user.getToken(user),String.valueOf(System.currentTimeMillis()));
                            //Log.info(redisUtil.get(userName+user.getToken(user)).toString());
                            //Log.info(redisUtil.get(userName));
                            result.setStatus(ConstantSrting.STATUS_SUCCESS);
                            result.setEntity(redisUtil.get(userName));
                            result.setMessage("获取Token成功,有效时间为60min");
                            return result;
                        }else{
                            result.setStatus(ConstantSrting.STATUS_FAIL);
                            result.setMessage("密码错误");
                            return result;
                        }
                    }else{
                        result.setStatus(ConstantSrting.STATUS_FAIL);
                        result.setMessage("账号错误");
                        return result;
                    }
                }

        }catch (Exception e){
            Log.error(e.getMessage());
            result.setStatus(ConstantSrting.STATUS_SUCCESS);
            result.setMessage(e.getMessage());
        }
        return result;
    }
    /**
     *功能描述
     * 通过参数检验token
     * @author shark
     * @date 2019/5/7
     * @param  * @param Authorization
     * @return com.linkjb.serviceregist.base.BaseResult<java.util.Map>
     */
    @PostMapping("/User/checkTokenByParam")
    public BaseResult<Map> checkToken(@RequestParam(value = "Authorization") String Authorization){
        BaseResult<Map> result = new BaseResult();
        try{
            String returnUserName = redisUtil.get(Authorization);
            if("".equals(returnUserName)||returnUserName==null){
                result.setStatus(ConstantSrting.STATUS_other);
                result.setMessage("token错误或已过期,请重新获取");
            }else{
                Map<String, User> hashEntries= (Map)redisUtil.getHashEntries("POJO_"+returnUserName);
                result.setEntity(hashEntries);
                result.setStatus(ConstantSrting.STATUS_SUCCESS);
                result.setMessage("token登录成功");
            }
        }catch (Exception e){
            Log.error(e.getMessage());
            result.setStatus(ConstantSrting.STATUS_FAIL);
            result.setMessage(e.getMessage());
        }
        return result;
    }
    /**
     *功能描述
     *  使用token登录并返回脱敏后的用户信息
     * @author shark
     * @date 2019/5/7
     * @param  * @param Authorization
     * @return com.linkjb.serviceregist.base.BaseResult<java.util.Map>
     */
   @GetMapping("/User/checkToken")
    public BaseResult<Map> checkTokenByHeader(@RequestHeader String Authorization){
        BaseResult<Map> result = new BaseResult();
        try{
            String returnUserName = redisUtil.get(Authorization);
            if("".equals(returnUserName)||returnUserName==null){
               result.setStatus(ConstantSrting.STATUS_other);
               result.setMessage("token错误或已过期,请重新获取");
            }else{
                Map<String, User> hashEntries= (Map)redisUtil.getHashEntries("POJO_"+returnUserName);
                result.setEntity(hashEntries);
                result.setStatus(ConstantSrting.STATUS_SUCCESS);
                result.setMessage("token登录成功");
            }
        }catch (Exception e){
            Log.error(e.getMessage());
            result.setStatus(ConstantSrting.STATUS_FAIL);
            result.setMessage(e.getMessage());
        }
       return result;
   }
    /**
     *功能描述
     * POST请求更新用户信息
     * @author shark
     * @date 2019/5/7
     * @param  * @param user
     * @return com.linkjb.serviceregist.base.BaseResult<java.lang.Boolean>
     */
   @PutMapping("/User/Update")
   @AuthToken
    public BaseResult<Boolean> UpdateUser(@RequestBody User user){
        BaseResult<Boolean> result = new BaseResult();
        try{
            if(!("".equals(user.getPassWord())||user.getPassWord()==null)){
                user.setPassWord(MD5.encryptPassword(user.getPassWord(),salt));
            }
            user.setUpdateTime(new Date());
            userService.update(user);
            redisUtil.set(user.getUserName(),user.getPassWord());
            //实体类转map
            JSONObject jsonObject  = (JSONObject)JSON.toJSON(user);
            Set<Map.Entry<String,Object>> entrySet = jsonObject.entrySet();
            Map<String,Object> map = new HashMap<>();
            for (Map.Entry<String,Object> entry:
                    entrySet) {
                map.put(entry.getKey(), entry.getValue());
            }
            redisUtil.putAll("POJO_"+user.getUserName(),map);
            result.setEntity(true);
            result.setMessage("更新成功");
            result.setStatus(ConstantSrting.STATUS_SUCCESS);
        }catch(Exception e){
            Log.error(e.getMessage());
            result.setMessage(e.getMessage());
            result.setStatus(ConstantSrting.STATUS_FAIL);
        }
        return result;
   }
}
