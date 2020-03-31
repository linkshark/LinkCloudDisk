package com.linkjb.servicews.controller;

/**
 * @ClassName WeChatController
 * @Description TODO
 * @Author shark
 * @Data 2020/2/21 22:00
 **/

import com.linkjb.servicews.enums.MessageEnums;
import com.linkjb.servicews.utils.CheckUtil;
import com.linkjb.servicews.utils.MessageDispatcher;
import com.linkjb.servicews.utils.MessageUtil;
import com.linkjb.servicews.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WeChatController {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    MessageDispatcher messageDispatcher;
    /**
     * 处理微信服务器发来的get请求，进行签名的验证
     *
     * signature 微信端发来的签名
     * timestamp 微信端发来的时间戳
     * nonce   微信端发来的随机字符串
     * echostr  微信端发来的验证字符串
     */
        private String TOKEN = "sharkshen";


        @GetMapping("/verify_wx_token")
        public String test(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr) {

            //排序
            String sortString = sort(TOKEN, timestamp, nonce);
            //加密
            String myString = sha1(sortString);
            //校验
            if (myString != null && myString != "" && myString.equals(signature)) {
                System.out.println("签名校验通过");
                //如果检验成功原样返回echostr，微信服务器接收到此输出，才会确认检验完成。
                return echostr;
            } else {
                System.out.println("签名校验失败");
                return "";
            }
        }

//    ToUserName 开发者微信号
//    FromUserName 发送方帐号（一个OpenID）
//    CreateTime 消息创建时间 （整型）
//    MsgType 消息类型，文本为text
//    Content 文本消息内容
//    MsgId 消息id，64位整型
    @PostMapping(value = "/verify_wx_token",produces = "text/xml;charset=utf-8")
    public String handler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, String> parseXml = MessageUtil.parseXml(request);
        String msgType = parseXml.get("MsgType");
        String content = parseXml.get("Content");
        System.out.println(msgType);
        System.out.println(content);
        request.setCharacterEncoding("UTF-8");
        if (MessageEnums.REQ_MESSAGE_TYPE_EVENT.equals(msgType)) {
            return messageDispatcher.processEvent(parseXml);
        }else{
            return messageDispatcher.processMessage(parseXml);
        }
    }



        public String sort(String token, String timestamp, String nonce) {
            String[] strArray = {token, timestamp, nonce};
            Arrays.sort(strArray);
            StringBuilder sb = new StringBuilder();
            for (String str : strArray) {
                sb.append(str);
            }

            return sb.toString();
        }

        public String sha1(String str) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-1");
                digest.update(str.getBytes());
                byte messageDigest[] = digest.digest();
                // Create Hex String
                StringBuffer hexString = new StringBuffer();
                // 字节数组转换为 十六进制 数
                for (int i = 0; i < messageDigest.length; i++) {
                    String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                    if (shaHex.length() < 2) {
                        hexString.append(0);
                    }
                    hexString.append(shaHex);
                }
                return hexString.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return "";
        }

}