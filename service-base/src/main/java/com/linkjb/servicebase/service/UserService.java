package com.linkjb.servicebase.service;

import com.linkjb.servicebase.pojo.LinkMedia;

import java.util.List;
import java.util.Queue;

public interface UserService {
    public List<LinkMedia> getLinkMedia(String orderId);
    public void shutdown();
    public Queue<Object> getMsgQueue();
}
