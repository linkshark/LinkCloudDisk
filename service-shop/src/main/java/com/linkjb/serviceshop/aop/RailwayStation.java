package com.linkjb.serviceshop.aop;

/**
 * @ClassName RailwayStation
 * @Description RailwayStation 实现 TicketService
 * @Author shark
 * @Data 2019/12/19 10:25
 **/
public class RailwayStation implements TicketService{
    @Override
    public void sellTicket() {
        System.out.println("售票............");

    }

    @Override
    public void inquire() {
        System.out.println("问询.............");
    }

    @Override
    public void withdraw() {
        System.out.println("退票.............");
    }
}
