package com.linkjb.servicewebsocket.service;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MyHandler implements WebSocketHandler {
    Logger log = LoggerFactory.getLogger(MyHandler.class);
    //在线用户列表
    private static final Map<String,WebSocketSession> users ;

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
                    JSONObject jsonObject = JSONObject.fromObject(webSocketMessage.getPayload());
                    //System.out.println(jsonObject.get("id"));
                    log.info(jsonObject.get("message")+":来自"+(String)webSocketSession.getAttributes().get("WEBSOCKET_USERID")+"的消息");
                    sendMessageToUser(jsonObject.get("id")+"",new TextMessage("服务器收到了，hello!"));
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
    public boolean sendMessageToUser(String clientId, TextMessage message) {
        if (users.get(clientId) == null) return false;
        WebSocketSession session = users.get(clientId);
       // System.out.println("sendMessage:" + session);
        if (!session.isOpen()) return false;
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

    @Override
    public boolean supportsPartialMessages() {
        return false;
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
