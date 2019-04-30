package com.linkjb.serviceregist.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author sharkshen
 * @description 记录登录信息的切面
 * @data 2019/4/30 10:30
 */
@Aspect
@Component
public class WebLogAcpect {
    private Logger log = LoggerFactory.getLogger(WebLogAcpect.class);
    /**
     *功能描述
     * @author shark
     * @date 2019/4/30
     * @param  * @param
     * @return void
     * 定义切入点，切入点为com.linkjb.serviceregist.controller.UserController下的所有函数
     */
    @Pointcut("execution(* com.linkjb.serviceregist.controller..*.*(..))")
    //@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void webLog(){};

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        //收到请求,记录内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //记录一下内容
        Object[] obj = joinPoint.getArgs();
        log.info("URL:" + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("Session : "+request.getSession().toString());
        //log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //获取请求参数
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String,String> parameterMap = new HashMap<>();
        while (enumeration.hasMoreElements()){
            String parameter = enumeration.nextElement();
            parameterMap.put(parameter,request.getParameter(parameter));
        }
        String str = JSON.toJSONString(parameterMap);
        if(obj.length > 0) {
            log.info("请求的参数信息为："+str);
        }

    }

    /**
     *功能描述
     *后置返回通知
     *这里需要注意的是:
     *  如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     *如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     *returning：限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，
     * 对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     * @author shark
     * @date 2019/4/30
     * @param  * @param joinPoint
     * @param ret
     * @return void
     */
    @AfterReturning(returning = "ret" , pointcut = "webLog()")
    public void doAfterReturning(JoinPoint joinPoint,Object ret){
        //处理完请求,返回内容
        log.info("第一个后置通知返回值" + ret);
        //log.info("RESPONSE" + ret);
    }

    @AfterReturning(returning = "ret" , pointcut = "webLog()")
    public void doAfterReturning2(JoinPoint joinPoint,Object ret){
        //处理完请求,返回内容
        log.info("第二个后置返回通知返回值"+ret);
    }
    /**
     *功能描述
     * 定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     * throwing:限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     * 对于throwing对应的通知方法参数为Throwable类型将匹配任何异常
     * @author shark
     * @date 2019/4/30
     * @param @param joinPoint
     * @param @param joinPoint
     * @return
     */

    @AfterThrowing(value = "webLog()" ,throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception) {
        //目标方法名：
        log.info(joinPoint.getSignature().getName());
        log.info("切面获取的异常信息" + exception.getMessage());
        if (exception instanceof NullPointerException) {
            log.info("发生了空指针异常!!!!!");
        }
    }

    /**
     *功能描述
     * 后置最终通知（目标方法只要执行完了就会执行后置通知方法）
     * @author shark
     * @date 2019/4/30
     * @param  * @param joinPoint
     * @return void
     */
    @After(value = "webLog()")
    public void doAfterAdvice(JoinPoint joinPoint){
        log.info("后置最终通知执行了!!!!");
    }

}
