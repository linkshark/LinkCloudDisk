package com.linkjb.serviceshop.aop;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * @ClassName TicketServiceThrowsAdvice
 * @Description 抛出异常时的处理意见
 * @Author shark
 * @Data 2019/12/19 10:32
 **/
public class TicketServiceThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(Exception ex){
        System.out.println("AFTER_THROWING....");
    }
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex){
       System.out.println("调用过程出错啦！！！！！");
    }

}
