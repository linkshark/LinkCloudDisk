package com.linkjb.serviceshop.aop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @ClassName TicketServiceAfterReturningAdvice
 * @Description 返回结果时后的处理意见
 * @Author shark
 * @Data 2019/12/19 10:26
 **/
public class TicketServiceAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("AFTER_RETURNING：本次服务已结束....");
    }
}
