package com.linkjb.servicewebsocket;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author sharkshen
 * @description
 * @data 2019/5/30 15:44
 */
public class LocalTimeTest {
    @Test
    public void TimeTest1(){
        LocalTime time = LocalTime.of(20, 13, 54); // 20:13:54
        int hour = time.getHour(); // 20
        int minute = time.getMinute(); // 13
        int second = time.getSecond(); // 54

        LocalDate date = LocalDate.parse("2018-04-20");
         time = LocalTime.parse("20:13:54");




//        LocalDateTime是LocalDate和LocalTime的组合形式，包含了年月日时分秒信息。举些LocalDateTime的使用示例：
        LocalDateTime ldt1 = LocalDateTime.of(2018, 4, 20, 20, 13, 54); // 2018-04-20T20:13:54
        LocalDateTime ldt2 = LocalDateTime.of(date, time); // 2018-04-20T20:13:54
//        LocalDateTime可以转换为LocalDate和LocalTime，转换后包含的信息减少了：
        LocalDate date1 = ldt1.toLocalDate(); // 2018-04-20
        LocalTime time1 = ldt1.toLocalTime(); // 20:13:54

        LocalDateTime ldt3 = date.atTime(time); // 2018-04-20T20:13:54
        LocalDateTime ldt4 = date.atTime(20, 13, 54); // 2018-04-20T20:13:54
        LocalDateTime ldt5 = time.atDate(date); // 2018-04-20T20:13:54

//        Duration用于计算两个LocalTime或者LocalDateTime的时间差，例如：
            LocalDate today = LocalDate.now();
            LocalTime nowTime = LocalTime.now();
            LocalDateTime NOWNOW = LocalDateTime.of(today,nowTime);

            LocalDateTime beforeTime = LocalDateTime.of(2018,3,12,23,00,00);
        Duration between = Duration.between(NOWNOW, beforeTime);
        System.out.println(between.getSeconds());



        System.out.println(between.getSeconds());
        System.out.println(between.getSeconds());



    }
}
