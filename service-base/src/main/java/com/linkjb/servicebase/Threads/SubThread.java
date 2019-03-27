package com.linkjb.servicebase.Threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *  2019/3/27
 */

@Component
@Scope("prototype")//多例
public class SubThread  implements Runnable{
    private static Logger log = LoggerFactory.getLogger(SubThread.class);

    private String subId;

    public SubThread(String orderId){
        this.subId = orderId;
    }


    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    @Override
    public void run() {
        //查询media表并返回所有订阅的url
        log.info("多线程已经处理订阅服务,订阅mediaId: "+subId);
    }
}
