package com.linkjb.servicewebsocket.service;

import com.alibaba.fastjson.JSON;
import com.linkjb.servicewebsocket.feign.UserFeignService;
import com.linkjb.servicewebsocket.service.Impl.MQServiceSendImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MyHandler implements WebSocketHandler {

    Logger log = LoggerFactory.getLogger(MyHandler.class);

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    //在线用户列表
    public static final Map<String,WebSocketSession> users ;

    private MQServiceSendImpl mqService;

    private UserFeignService userFeignService;
    static {
        users = new ConcurrentHashMap<>();
    }

    //新增socket
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String ID = session.getUri().toString().split("ID=")[1];
        log.info("用户:"+ID +" 建立连接!");
        if(ID!=null){
            users.put(ID,session);
            log.info(users.toString());
            session.sendMessage(new TextMessage("成功建立SOCKET连接"));
            //System.out.println(ID);
            //System.out.println(session);
            log.info("当前的session为"+session);
        }
        log.info("当前在线人数:"+users.size());
    }
    //接收socket信息
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
                try{
                    String payload = (String)webSocketMessage.getPayload();
                    com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(payload);
                    mqService = applicationContext.getBean(MQServiceSendImpl.class);
                    if(jsonObject.get("sendToAll")==null){
                        mqService.sendTo(jsonObject);
                    }else{
                        mqService.sendToAll(jsonObject);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
    }

    /**
     * 发送信息给指定用户
     * @param clientId
     * @param message
     * @return
     */
    @Async//交由异步线程池处理,提高反应速度
    public boolean sendMessageToUser(String clientId, TextMessage message) {
        if(users.get(clientId) == null) {return false;}
        WebSocketSession session = users.get(clientId);
         // System.out.println("sendMessage:" + session);
        if (!session.isOpen()) {
            return false;
        }
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * 广播信息
     * @param message
     * @return
     */
    public boolean sendMessageToAllUsers(TextMessage message) {
        boolean allSendSuccess = true;
        Set<String> clientIds = users.keySet();
        WebSocketSession session = null;
        for (String clientId : clientIds) {
            try {
                session = users.get(clientId);
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                allSendSuccess = false;
            }
        }

        return  allSendSuccess;
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        log.error("连接出错");
        users.remove(getClientId(session));

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //System.out.println("连接已关闭：" + status);
        log.info(session.getAttributes().get("WEBSOCKET_USERID").toString()+"连接已关闭：" + status);
        log.info("当前在线用户为" +session);
        //System.out.println(users);
        users.remove(getClientId(session));
    }

    /*
     * 是否支持消息拆分发送：如果接收的数据量比较大，最好打开(true), 否则可能会导致接收失败。
     * 如果出现WebSocket连接接收一次数据后就自动断开，应检查是否是这里的问题。
     */
    @Override
    public boolean supportsPartialMessages() {
        return true;
    }
    /**
     * 获取用户标识
     * @param session
     * @return
     */
    private String getClientId(WebSocketSession session) {
        try {
            String clientId = (String) session.getAttributes().get("WEBSOCKET_USERID");
            return clientId;
        } catch (Exception e) {
            return null;
        }
    }



}
