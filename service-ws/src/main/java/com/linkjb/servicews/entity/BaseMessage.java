package com.linkjb.servicews.entity;

import lombok.Data;

/**
 * @ClassName BaseMessage
 * @Description TODO
 * @Author shark
 * @Data 2020/2/22 14:08
 **/
@Data
public class BaseMessage {
    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;
    private long MsgId;
    //省略 getter/setter
}

