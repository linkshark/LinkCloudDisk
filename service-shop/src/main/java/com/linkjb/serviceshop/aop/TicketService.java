package com.linkjb.serviceshop.aop;

/**
 * @ClassName TicketService
 * @Description 售票服务
 * @Author shark
 * @Data 2019/12/19 10:24
 **/
public interface TicketService {
    //售票
    public void sellTicket();

    //问询
    public void inquire();

    //退票
    public void withdraw();

}
