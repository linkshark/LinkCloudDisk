package com.linkjb.serviceshop.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @ClassName TicketServiceBeforeAdvice
 * @Description TODO
 * @Author shark
 * @Data 2019/12/19 10:26
 **/
public class TicketServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("BEFORE_ADVICE: 欢迎光临代售点....");

    }
}
