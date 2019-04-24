package com.linkjb.serviceregist.filter;

import com.alibaba.fastjson.JSONObject;
import com.linkjb.serviceregist.annotation.AuthToken;
import com.linkjb.serviceregist.service.UserService;
import com.linkjb.serviceregist.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author sharkshen
 * @data 2019/1/24 11:09
 * @Useage
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    //日志记录
    private static Logger log = LoggerFactory.getLogger(AuthenticationInterceptor.class);
    @Autowired
    RedisUtil redisUtil;
    //存放鉴权信息的Header名称，默认是Authorization
    private String httpHeaderName = "Authorization";//Authorization

    //鉴权失败后返回的错误信息，默认为401 unauthorized
    private String unauthorizedErrorMessage = "401 unauthorized";

    //鉴权失败后返回的HTTP错误码，默认为401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    //存放登录用户模型Key的Request Key
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";

    @Override
//    预处理回调方法,实现处理器的预处理，第三个参数为响应的处理器,自定义Controller,返回值为true表示继续流程（如调用下一个拦截器或处理器）
//    或者接着执行postHandle()和afterCompletion()；false表示流程中断，不会继续调用其他的拦截器或处理器，中断执行。
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //log.info(handler.getClass().toString());
        if (!(handler instanceof org.springframework.web.method.HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 如果打上了AuthToken注解则需要验证token
        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {

            String token = request.getHeader(httpHeaderName);
            //log.info("Get token from request is {} ", token);
            String username = "";

            if (token != null && token.length() != 0) {
                username = redisUtil.get(token);
                //log.info("Get username from Redis is {}", username);
            }
            if (username != null && !username.trim().equals("")) {
                //log.info((String)redisUtil.get(username+token));
                Long tokeBirthTime = Long.valueOf((String)redisUtil.get(username+token));
                //log.info("token Birth time is: {}", tokeBirthTime);
                Long diff = System.currentTimeMillis() - tokeBirthTime;
                //log.info("token is exist : {} ms", diff);
                if (diff > 1000*60*60) {
                    //设置过期时间
                    redisUtil.expire(username,1000*60*60, TimeUnit.MILLISECONDS);
                    redisUtil.expire(token,1000*60*60, TimeUnit.MILLISECONDS );
                    log.info("Reset expire time success!");
                    Long newBirthTime = System.currentTimeMillis();
                    redisUtil.set(token + username, newBirthTime.toString());
                }

                //用完关闭
                request.setAttribute(REQUEST_CURRENT_KEY, username);
                return true;
            } else {
                JSONObject jsonObject = new JSONObject();

                PrintWriter out = null;
                try {
                    response.setStatus(unauthorizedErrorCode);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                    jsonObject.put("code", ((HttpServletResponse) response).getStatus());
                    jsonObject.put("message", HttpStatus.UNAUTHORIZED);
                    out = response.getWriter();
                    out.println(jsonObject);

                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != out) {
                        out.flush();
                        out.close();
                    }
                }

            }

        }

        request.setAttribute(REQUEST_CURRENT_KEY, null);

        return true;
    }

    @Override
//    后处理回调方法，实现处理器的后处理（DispatcherServlet进行视图返回渲染之前进行调用），\
//    此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
//    整个请求处理完毕回调方法,该方法也是需要当前对应的Interceptor的preHandle()的返回值为true时才会执行，也就是在DispatcherServlet渲染了对应的视图之后执行。
//    用于进行资源清理。
//    整个请求处理完毕回调方法。如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，
//    类似于try-catch-finally中的finally，但仅调用处理器执行链中
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}