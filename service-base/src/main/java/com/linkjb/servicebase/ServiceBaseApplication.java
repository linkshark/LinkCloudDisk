package com.linkjb.servicebase;

import com.linkjb.servicebase.dao.TestDao;
import com.linkjb.servicebase.pojo.NameData;
import com.linkjb.servicebase.utils.MakeData;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class ServiceBaseApplication {

    public static void main(String[] args) {

        SpringApplication.run(ServiceBaseApplication.class, args);

    }

}
