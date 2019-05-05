package com.linkjb.servicewebsocket.filter;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class WebSocketInterceptor implements HandshakeInterceptor {
    @Override
    //在握手之前执行该方法, 继续握手返回true, 中断握手返回false. 通过attributes参数设置WebSocketSession的属性
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
        if(request instanceof ServletServerHttpRequest){
            String ID = request.getURI().toString().split("ID=")[1];
            System.out.println("当前的session的ID="+ID);
            //ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
            //HttpSession session = serverHttpRequest.getServletRequest().getSession();
            attributes.put("WEBSOCKET_USERID",ID);
        }
        return true;
    }
    //完成握手之后执行该方法
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        System.out.println("进入webSocket的afterHandshake拦截器！");
    }
}
