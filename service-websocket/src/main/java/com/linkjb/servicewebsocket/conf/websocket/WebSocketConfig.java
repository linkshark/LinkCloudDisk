package com.linkjb.servicewebsocket.conf.websocket;

import com.linkjb.servicewebsocket.filter.WebSocketInterceptor;
import com.linkjb.servicewebsocket.service.MyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 通过实现接口来配置websocket请求的路径和拦截
 * 实现WebSocketConfigurer接口，
 * 重写registerWebSocketHandlers方法，这是一个核心实现方法，
 * 配置websocket入口，允许访问的域、注册Handler、定义拦截器。
 * 客户端通过“/webSocket/{ID}”直接访问Handler核心类，进行socket的连接、接收、发送等操作，
 * 这里由于还加了个拦截器，所以建立新的socket访问时，
 * 都先进来拦截器再进去Handler类，“new WebSocketInterceptor()”是我实现的拦截器，
 * “new MyHandler()”是我实现的一个Handler类。
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Bean
    public WebSocketHandler webSocketHandler() {
        return new MyHandler();
    }
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
            //handler是websocket的核心,配置入口
        registry.addHandler(new MyHandler(),"/webSocket/{ID}").setAllowedOrigins("*").addInterceptors(new WebSocketInterceptor());
    }
}
