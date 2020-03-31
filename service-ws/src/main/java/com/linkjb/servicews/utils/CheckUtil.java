package com.linkjb.servicews.utils;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @ClassName CheckUtil
 * @Description TODO
 * @Author shark
 * @Data 2020/2/21 22:32
 **/
public class CheckUtil {
    private static final String token = "sharkshen";
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] str = new String[]{token, timestamp, nonce};
        //排序
        Arrays.sort(str);
        //拼接字符串
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            buffer.append(str[i]);
        }
        //进行sha1加密
        String temp = SHA1.encode(buffer.toString());
        //与微信提供的signature进行匹对
        return signature.equals(temp);
    }
    //判断是否为纯数字
    public static boolean isNumeric(String str) {
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }
        return true;
    }


}
