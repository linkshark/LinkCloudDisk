package com.linkjb.servicewebsocket;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.TemporalAdjusters.*;

/**
 * @author sharkshen
 * @description
 * @data 2019/5/30 15:29
 */
public class LocalDateTest {

    @Test
    public void test01(){
        System.out.println(LocalDate.now());
        LocalDate date = LocalDate.of(2018,12,20);
        int year = date.getYear();
        int month = date.getMonth().getValue();
        int day = date.getDayOfMonth();
        // 查看该月有多少天
        int days = date.lengthOfMonth();
         // 是否是闰年
        boolean isLeap = date.isLeapYear();

//        除了调用LocalDate的getYear方法外，我们也可以使用ChronoField枚举类型来实现相同的功能：
        int year1 = date.get(ChronoField.YEAR); //
        int month1 = date.get(ChronoField.MONTH_OF_YEAR);
        int day1 = date.get(ChronoField.DAY_OF_MONTH);
         // 当前日期属于该月第几周
        int weekOfMonth = date.get(ChronoField.ALIGNED_WEEK_OF_MONTH);

        int dayOfWeek = date.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR);
        System.out.println(weekOfMonth);
        System.out.println(dayOfWeek);


        LocalDate date3 = LocalDate.of(2018, 4, 20); // 2018-04-20
        LocalDate date4 = date3.withDayOfMonth(22); // 2018-04-22
        LocalDate date5 = date3.with(ChronoField.DAY_OF_MONTH, 22); // 2018-04-22
        LocalDate date6 = date3.withYear(2019); // 2019-04-20
        LocalDate date7 = date3.plusDays(5); // 2018-04-25
        LocalDate date8 = date3.plus(5, ChronoUnit.DAYS); // 2018-04-25
        LocalDate date9 = date3.minusYears(10); // 2008-04-20



//      TemporalAdjusters类提供了许多静态方法来修改LocalDate对象。
//      当我们需要获取下一个周天，下一个工作日，本月的最后一天等信息时，TemporalAdjusters类便可派上用场：
        LocalDate with = date3.with(nextOrSame(DayOfWeek.MONDAY));// 2018-04-23
        LocalDate date11 = date3.with(lastDayOfMonth()); // 2018-04-30
        LocalDate date12 = date3.with(previous(DayOfWeek.SATURDAY)); // 2018-04-14

        String str1 = date.format(DateTimeFormatter.BASIC_ISO_DATE); // 20180420
        String str2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE); // 2018-04-20

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String str5 = date.format(dtf); // 2018-04-20
        LocalDate date13 = LocalDate.parse(str5, dtf); // 2018-04-20
    }
}
