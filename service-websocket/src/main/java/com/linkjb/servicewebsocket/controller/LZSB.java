package com.linkjb.servicewebsocket.controller;

import java.math.BigDecimal;

/**
 * @ClassName LZSB
 * @Description TODO
 * @Author shark
 * @Data 2020/3/3 21:53
 **/
public class LZSB {

    public static void main(String[] args){
//        某投资人若十年内每年年末投资10万元 年利率为8% 问十年末本利和为多少
        BigDecimal s = new BigDecimal(0);
        for(int i=0;i<9;i++){
            s = s.add(new BigDecimal(100000));
            s = s.multiply(new BigDecimal(1.08));
        }
        s = s.add(new BigDecimal(100000));
        System.out.println(s);
    }
}
